package com.hwq.dataloom.controller;

import com.hwq.dataloom.framework.errorcode.ErrorCode;
import com.hwq.dataloom.framework.result.BaseResponse;
import com.hwq.dataloom.framework.result.ResultUtils;
import com.hwq.dataloom.framework.exception.ThrowUtils;
import com.hwq.dataloom.model.json.StructDatabaseConfiguration;
import com.hwq.dataloom.model.dto.datasource.PreviewData;
import com.hwq.dataloom.model.dto.datasource.PreviewDataRequest;
import com.hwq.dataloom.model.entity.DatasourceMetaInfo;
import com.hwq.dataloom.framework.model.entity.User;
import com.hwq.dataloom.service.DatasourceMetaInfoService;
import com.hwq.dataloom.service.UserService;
import com.hwq.dataloom.utils.datasource.MySQLUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author HWQ
 * @date 2024/5/24 01:28
 * @description 数据源接口
 */
@RestController
@RequestMapping("/datasource")
public class DataSourceController {
    @Resource
    private UserService userService;
    @Resource
    private DatasourceMetaInfoService datasourceMetaInfoService;

    /**
     * 检验连接
     * @param structDatabaseConfiguration
     * @param request
     * @return
     */
    @PostMapping("/checkValid")
    public BaseResponse<Boolean> checkConnect(@RequestBody @Valid StructDatabaseConfiguration structDatabaseConfiguration, HttpServletRequest request) {
        // 校验参数
        validDataSourceConfig(structDatabaseConfiguration);
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        // todo：改为策略模式动态选择校验方法
        return ResultUtils.success(MySQLUtil.checkConnectValid(structDatabaseConfiguration));
    }

    /**
     * 获取表结构
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getSchemas/{id}")
    public BaseResponse<List<String>> getSchemas(@PathVariable("id") Long id, HttpServletRequest request) {
        // 校验参数
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        DatasourceMetaInfo datasourceMetaInfo = datasourceMetaInfoService.getById(id);
        ThrowUtils.throwIf(datasourceMetaInfo == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!loginUser.getId().equals(datasourceMetaInfo.getUserId()), ErrorCode.NO_AUTH_ERROR);
        return ResultUtils.success(datasourceMetaInfoService.getSchemas(datasourceMetaInfo));
    }

    /**
     * 显示MySQL连接数据
     * @param previewDataRequest
     * @param request
     * @return
     */
    @PostMapping("/previewData")
    public BaseResponse<PreviewData> previewData(@RequestBody PreviewDataRequest previewDataRequest, HttpServletRequest request) {
        // 校验参数
        ThrowUtils.throwIf(previewDataRequest == null, ErrorCode.PARAMS_ERROR);
        String datasourceId = previewDataRequest.getDatasourceId();
        String dataName = previewDataRequest.getDataName();
        ThrowUtils.throwIf(datasourceId == null, ErrorCode.PARAMS_ERROR, "datasourceId不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(dataName), ErrorCode.PARAMS_ERROR, "dataName");
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        DatasourceMetaInfo datasourceMetaInfo = datasourceMetaInfoService.getById(datasourceId);
        ThrowUtils.throwIf(datasourceMetaInfo == null, ErrorCode.NOT_FOUND_ERROR);
        ThrowUtils.throwIf(!loginUser.getId().equals(datasourceMetaInfo.getUserId()), ErrorCode.NO_AUTH_ERROR);
        return ResultUtils.success(datasourceMetaInfoService.PreviewData(previewDataRequest, datasourceMetaInfo));
    }

    /**
     * 新增数据源信息
     * @param structDatabaseConfiguration
     * @param request
     * @return
     */
    @PostMapping("/save")
    public BaseResponse<Boolean> saveDataSourceMetaInfo(@RequestBody @Valid StructDatabaseConfiguration structDatabaseConfiguration, HttpServletRequest request) {
        // 校验参数
        validDataSourceConfig(structDatabaseConfiguration);
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        return ResultUtils.success(datasourceMetaInfoService.saveDataSourceMetaInfo(structDatabaseConfiguration, loginUser));
    }

    /**
     * 校验数据
     * @param structDatabaseConfiguration
     */
    public void validDataSourceConfig(StructDatabaseConfiguration structDatabaseConfiguration) {
        ThrowUtils.throwIf(structDatabaseConfiguration == null, ErrorCode.PARAMS_ERROR);
        String name = structDatabaseConfiguration.getName();
        String host = structDatabaseConfiguration.getHost();
        String port = structDatabaseConfiguration.getPort();
        String dataBaseName = structDatabaseConfiguration.getDataBaseName();
        String userName = structDatabaseConfiguration.getUserName();
        String password = structDatabaseConfiguration.getPassword();
        ThrowUtils.throwIf(StringUtils.isEmpty(name), ErrorCode.PARAMS_ERROR, "name不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(host), ErrorCode.PARAMS_ERROR, "host不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(port), ErrorCode.PARAMS_ERROR, "port不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(dataBaseName), ErrorCode.PARAMS_ERROR, "dataBaseName不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(userName), ErrorCode.PARAMS_ERROR, "userName不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(password), ErrorCode.PARAMS_ERROR, "password不得为空");
        ThrowUtils.throwIf(StringUtils.isEmpty(password), ErrorCode.PARAMS_ERROR, "password不得为空");
    }
}
