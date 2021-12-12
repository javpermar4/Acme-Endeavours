package acme.features.anonymous.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousDutyRepository extends AbstractRepository{
	
	@Query("select d from Duty d where d.endMoment > current_timestamp() and d.visibility=0 order by d.initialMoment DESC")
	Collection<Duty> findMany();
	
	@Query("select d from Duty d where d.id = ?1 and d.visibility=0 and d.endMoment > current_timestamp()")
	Duty findOneDutyById(int id);
	
	@Query("select DATEDIFF(d.endMoment, d.initialMoment) from Duty d where d.id LIKE ?1")
	Double getExecutionPeriod(int id);
}
