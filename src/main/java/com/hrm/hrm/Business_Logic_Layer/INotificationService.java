package com.hrm.hrm.Business_Logic_Layer;

import com.hrm.hrm.dto.NotificationsDto;
import com.hrm.hrm.entity.Notifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface INotificationService {
Page<Notifications> getAllNotification(Pageable pageable,String search,Integer departmentId);
List<Notifications> GetNotificationByDepartmentId(int id);
void CreateNotification(Notifications notifications);
}
