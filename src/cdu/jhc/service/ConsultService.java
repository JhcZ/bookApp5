package cdu.jhc.service;

import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import java.util.List;

public interface ConsultService {
    //根据id获取咨询
    Consult get(int id);

    //根据图书id获取所有咨询
    List<Consult> getByBookId(int bookId,ConsultStatus status);

    //分页获取所有咨询
    List<Consult> get(Consult condition,int page,int pageSize);

    //添加顾客咨询
    boolean add(Consult consult);

    //管理员回复咨询
    boolean reply(Consult consult);

    //删除顾客咨询
    boolean del(int id);

    //根据图书id删除所有咨询
    boolean delByBookId(int bookId,ConsultStatus status);

    //获取咨询数量
    int count(Consult condition);
}
