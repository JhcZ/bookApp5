package cdu.jhc.service.impl;

import cdu.jhc.dao.ConsultDao;
import cdu.jhc.dao.impl.ConsultDaoImpl;
import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import cdu.jhc.service.ConsultService;

import java.util.List;

public class ConsultServiceImpl implements ConsultService{
    ConsultDao consultDao = new ConsultDaoImpl();

    //根据id获取咨询
    @Override
    public Consult get(int id) {
        return consultDao.findById(id);
    }

    //根据图书id获取所有咨询
    @Override
    public List<Consult> getByBookId(int bookId, ConsultStatus status) {
        Consult condition = new Consult();
        condition.setBookId(bookId);
        condition.setReStatus(status);
        return consultDao.query(condition);
    }

    //分页获取所有咨询
    @Override
    public List<Consult> get(Consult condition, int page, int pageSize) {
        return consultDao.query(condition,(page - 1) * pageSize,pageSize);
    }

    //添加顾客咨询
    @Override
    public boolean add(Consult consult) {
        return consultDao.insert(consult) == 1;
    }

    //管理员回复咨询
    @Override
    public boolean reply(Consult consult) {
        return consultDao.update(consult) == 1;
    }

    //删除顾客咨询
    @Override
    public boolean del(int id) {
        return consultDao.delete(id) == 1;
    }

    //根据图书id删除所有咨询
    @Override
    public boolean delByBookId(int bookId, ConsultStatus status) {
        return consultDao.deleteByBookId(bookId,status) == 1;
    }

    //获取咨询数量
    @Override
    public int count(Consult condition) {
        return consultDao.count(condition);
    }
}
