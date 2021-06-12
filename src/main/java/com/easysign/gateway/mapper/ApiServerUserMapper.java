package com.easysign.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easysign.gateway.entity.db.ApiServerUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.mapper
 * @ClassName: ApiServerUserMapper
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:55
 */
@Mapper
public interface ApiServerUserMapper extends BaseMapper<ApiServerUser> {

}
