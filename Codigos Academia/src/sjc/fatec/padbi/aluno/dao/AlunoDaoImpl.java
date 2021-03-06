package sjc.fatec.padbi.aluno.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sjc.fatec.padbi.aluno.model.Aluno;

@Transactional
@Repository
public class AlunoDaoImpl implements AlunoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Serializable cadastrar(Aluno aluno) {
		return getSession().save(aluno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> listar() {
		return ((List<Aluno>) getCriteria(Aluno.class).list());
	}
	
	@Override
	public Aluno buscar(Long id) {
		return (Aluno) getCriteria(Aluno.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	@Override
	public Aluno editar(Aluno aluno) {
		return (Aluno) getSession().merge(aluno);
	}

	private Criteria getCriteria(Class<?> clazz) {
		return getSession().createCriteria(clazz);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
