package controller;

//Sheri Westhoff
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Artwork;

public class ArtGalleryHelper {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebArtGalleryWesthoffNew");

	public void insertItem(Artwork toAdd) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();

	}

	public List<Artwork> showAllItems() {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		// creates the query but does not execute it.
		TypedQuery<Artwork> allResults = em.createQuery("Select list_item from Artwork list_item", Artwork.class);
		// Selects the info
		List<Artwork> allItems = allResults.getResultList();
		// need to close the entity manager
		em.close();

		return allItems;
	}

	
	public void deleteItem(Artwork toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Artwork> typedQuery = em.createQuery(
				"select li from Artwork li where li.id = :selectedId",
				Artwork.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		typedQuery.setMaxResults(1);
		Artwork result = typedQuery.getSingleResult(); 
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	// used before adding web piece
	
//	public void deleteItem(Artwork toDelete) {
//		// TODO Auto-generated method stub
//		EntityManager em = emfactory.createEntityManager();
//		em.getTransaction().begin();
//		// Creating they query but does not execute
//		TypedQuery<Artwork> typedQuery = em.createQuery(
//				"Select list_item from Artwork list_item "
//						+ "where list_item.title = :selectedTitle and list_item.artistName = :selectedArtistName",
//				Artwork.class);
//		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
//		typedQuery.setParameter("selectedArtistName", toDelete.getArtistName());
//		// Gets the 1st one in the table
//		typedQuery.setMaxResults(1);
//
//		Artwork result = typedQuery.getSingleResult();
//
//		em.remove(result);
//		em.getTransaction().commit();
//		System.out.println("Record has been deleted");
//		em.close();
//	}

	public Artwork searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		Artwork foundItem = em.find(Artwork.class, idToEdit);
		em.close(); // close
		// return the results of the query
		return foundItem;
	}

	public void updateArtwork(Artwork toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close(); // close

	}

	public List<Artwork> searchByItemByArtist(String artistName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Creating they query but does not execute
		TypedQuery<Artwork> typedQuery = em.createQuery(
				"Select list_item from Artwork list_item" + " where list_item.artistName = :selectedArtist",
				Artwork.class);
		typedQuery.setParameter("selectedArtist", artistName);

		List<Artwork> result = typedQuery.getResultList();
		em.close();
		return result;
	}

	public List<Artwork> searchForItemByMedia(String mediaName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		// Creating they query but does not execute
		TypedQuery<Artwork> typedQuery = em.createQuery(
				"Select list_item from Artwork list_item " + "where list_Item.media = :selectedMedia", Artwork.class);
		// Set variable name
		typedQuery.setParameter("selectedMedia", mediaName);
		// list out the results from the query
		List<Artwork> result = typedQuery.getResultList();
		em.close(); // close
		// return the results of the query
		return result;
	}

}
