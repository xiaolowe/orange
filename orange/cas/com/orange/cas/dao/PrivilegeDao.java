package com.orange.cas.dao;import java.util.List;import java.util.Map;import com.orange.cas.entity.PrivilegeEntity;import com.orange.core.base.dao.BaseDao;import com.orange.core.base.dao.DaoManager;import com.orange.core.base.dao.page.PagerModel;import javax.annotation.Resource;import org.springframework.stereotype.Repository;@Repository("casPrivilegeDao")public class PrivilegeDao implements DaoManager<PrivilegeEntity> {	    @Resource	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public int insert(PrivilegeEntity e) {		return dao.insert("cas.privilege.insert", e);	}		public int deleteById(String id) {		// TODO Auto-generated method stub		return dao.delete("cas.privilege.deleteById", id);	}		public int delete(PrivilegeEntity e) {		return dao.delete("cas.privilege.delete", e);	}		public int deletes(String[] ids) {		PrivilegeEntity e = new PrivilegeEntity();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}		public int update(PrivilegeEntity e) {		return dao.update("cas.privilege.update", e);	}	public PrivilegeEntity selectById(String id) {		return (PrivilegeEntity) dao.selectOne("cas.privilege.selectById",id);	}		public PrivilegeEntity selectOne(PrivilegeEntity e) {		return (PrivilegeEntity) dao.selectOne("cas.privilege.selectOne", e);	}		@SuppressWarnings({ "unchecked", "rawtypes" })	public List selectList(PrivilegeEntity e) {		return dao.selectList("cas.privilege.selectList", e);	}		public PagerModel selectPageList(PrivilegeEntity e) {		return dao.selectPageList("privilege.selectPageList",				"cas.privilege.selectPageCount", e);	}	@SuppressWarnings({ "unchecked", "rawtypes" })	public List selectList(Map<String, String> arg1) {		// TODO Auto-generated method stub		return dao.selectList("cas.privilege.selectList", arg1);	}}