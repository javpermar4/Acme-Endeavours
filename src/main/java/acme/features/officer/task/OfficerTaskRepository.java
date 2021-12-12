package acme.features.officer.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.endMoment < current_timestamp() and t.visibility = 0 order by t.initialMoment DESC")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select DATEDIFF(t.endMoment, t.initialMoment) from Task t where t.id LIKE ?1")
	Double getExecutionPeriod(int id);
	
	@Query("select t from Task t where t.officer.id = ?1")
	Collection<Task> findManyTasksByOfficerId(int id);
	
	@Query("select o from Officer o where o.userAccount.id = ?1")
	Officer findOneOfficerByUserAccountId(int id);
		
}
