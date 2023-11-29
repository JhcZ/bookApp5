package cdu.jhc.dao;

import cdu.jhc.model.Consult;
import cdu.jhc.model.ConsultStatus;
import java.util.List;

public interface ConsultDao extends SimpleDao<Consult>{
    //根据图书id删除所有咨询
    int deleteByBookId(int bookId,ConsultStatus status);
}
