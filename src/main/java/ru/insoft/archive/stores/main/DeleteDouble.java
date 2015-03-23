package ru.insoft.archive.stores.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Благодатских С.
 */
public class DeleteDouble {

	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
		EntityTransaction etx = em.getTransaction();

		etx.begin();
		List<Object[]> doubles = em.createNamedQuery("Fund.getDoubles").getResultList();
		int lastIndex = doubles.size() - 1;

		Map<Long, List<Long>> result = new HashMap<>();

		for (int i = 0; i < lastIndex;) {
			Object[] obj = doubles.get(i);
			Long mainId = (Long) obj[0];
			Integer number = (Integer) obj[1];

			obj = doubles.get(++i);
			List<Long> ids = new ArrayList<>();

			while (number.equals(obj[1]) && i < lastIndex) {
				ids.add((Long) obj[0]);
				obj = doubles.get(++i);
			}

			if (i == lastIndex) {
				if (number.equals(obj[1])) {
					ids.add((Long) obj[0]);
				} else {
					throw new RuntimeException("number is " + number + ", objnumber is " + obj[1]);
				}
			}
			result.put(mainId, ids);
		}

		for (Long key : result.keySet()) {
			List<Long> ids = result.get(key);
			em.createNamedQuery("Organization.updateFund")
					.setParameter("id", key)
					.setParameter("ids", ids)
					.executeUpdate();
			em.createNamedQuery("Fund.deleteByIds")
					.setParameter("ids", ids)
					.executeUpdate();
		}
		etx.commit();
		em.close();
		System.out.println("Deleted " + result.size() + " doubles.");	
		System.exit(0);
	}

	private static void showIds(List<Long> ids) {
		for (Long id : ids) {
			System.out.print(id + ", ");
		}
		System.out.println();
	}
}
