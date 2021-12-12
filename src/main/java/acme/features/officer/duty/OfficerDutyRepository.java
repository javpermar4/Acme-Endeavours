package acme.features.officer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerDutyRepository extends AbstractRepository{
	
	@Query("select d from Duty d where d.endMoment < current_timestamp() and d.visibility = 0 order by d.initialMoment DESC")
	Collection<Duty> findMany();
	
	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);
	
	@Query("select DATEDIFF(d.endMoment, d.initialMoment) from Duty d where d.id LIKE ?1")
	Double getExecutionPeriod(int id);
	
	@Query("select d from Duty d where d.officer.id = ?1")
	Collection<Duty> findManyDutiesByOfficerId(int id);
	
	@Query("select o from Officer o where o.userAccount.id = ?1")
	Officer findOneOfficerByUserAccountId(int id);
		
}
