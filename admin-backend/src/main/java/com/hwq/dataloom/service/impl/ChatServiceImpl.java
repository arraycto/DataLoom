package com.hwq.dataloom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwq.dataloom.framework.errorcode.ErrorCode;
import com.hwq.dataloom.framework.exception.ThrowUtils;
import com.hwq.dataloom.framework.model.entity.User;
import com.hwq.dataloom.model.entity.*;
import com.hwq.dataloom.model.vo.GetUserChatHistoryVO;
import com.hwq.dataloom.service.*;
import com.hwq.dataloom.mapper.ChatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author HWQ
* @description 针对表【chat】的数据库操作Service实现
* @createDate 2023-10-03 00:51:55
*/
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat>
    implements ChatService{

    @Resource
    private AiRoleService aiRoleService;

    @Resource
    private UserDataService userDataService;

    @Resource
    private UserCreateAssistantService userCreateAssistantService;

    @Override
    public List<GetUserChatHistoryVO> getUserChatHistory(User loginUser) {
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        QueryWrapper<Chat> qw = new QueryWrapper<>();
        qw.eq("userId", loginUser.getId());
        List<Chat> list = this.list(qw);
        List<GetUserChatHistoryVO> res = new ArrayList<>();
        for (Chat chat : list) {
            Long id = chat.getId();
            Long modelId = chat.getModelId();
            // modelId查询对应的助手信息
            AiRole aiRole = aiRoleService.getById(modelId);
            if(aiRole == null) { //可能是用户创建的助手
                UserCreateAssistant userCreateAssistant = userCreateAssistantService.getById(modelId);
                GetUserChatHistoryVO getUserChatHistoryVO = new GetUserChatHistoryVO();
                getUserChatHistoryVO.setChatId(id);
                getUserChatHistoryVO.setAssistantName(userCreateAssistant.getAssistantName());
                getUserChatHistoryVO.setFunctionDes(userCreateAssistant.getFunctionDes());
                res.add(getUserChatHistoryVO);
            }
            // 填充信息
            if (aiRole != null) {
                GetUserChatHistoryVO getUserChatHistoryVO = new GetUserChatHistoryVO();
                getUserChatHistoryVO.setChatId(id);
                getUserChatHistoryVO.setAssistantName(aiRole.getAssistantName());
                getUserChatHistoryVO.setFunctionDes(aiRole.getFunctionDes());
                res.add(getUserChatHistoryVO);
            }
        }
        return res;
    }

    @Override
    public Boolean addUserChatHistory(Long modelId, User loginUser) {
        ThrowUtils.throwIf(loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(modelId == null, ErrorCode.PARAMS_ERROR);
        // 添加记录
        Chat chat = new Chat();
        chat.setUserId(loginUser.getId());
        chat.setModelId(modelId);
        boolean save = this.save(chat);
        ThrowUtils.throwIf(!save, ErrorCode.SYSTEM_ERROR);
        return true;
    }

    @Override
    public Boolean addUserAskSqlHistory(Long dataId, User loginUser) {
        ThrowUtils.throwIf(loginUser.getId() == null, ErrorCode.NOT_LOGIN_ERROR);
        ThrowUtils.throwIf(dataId == null, ErrorCode.PARAMS_ERROR);
        UserData userData = userDataService.getById(dataId);
        ThrowUtils.throwIf(userData == null, ErrorCode.PARAMS_ERROR);
        // 添加记录
        Chat chat = new Chat();
        chat.setUserId(loginUser.getId());
        chat.setModelId(1782948306561814529L);
        chat.setDataId(dataId);
        boolean save = this.save(chat);
        ThrowUtils.throwIf(!save, ErrorCode.SYSTEM_ERROR);
        return true;
    }
}




