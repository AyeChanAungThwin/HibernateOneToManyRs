package acat.hibernate;

import java.util.List;

import acat.hibernate.dao.LaptopDao;
import acat.hibernate.dao.PersonDao;
import acat.hibernate.dependency.DependencyRegistry;
import acat.hibernate.dto.LaptopDto;
import acat.hibernate.dto.PersonDto;
import acat.hibernate.entity.FullName;
import acat.hibernate.entity.Laptop;
import acat.hibernate.entity.Person;

public class App {
	
	public static void main( String[] args ) {
		//saveFromLaptopEntity();
		saveFromPersonEntity();
    }
	
	private static void saveFromLaptopEntity() {
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Person person = dependency.createPerson(); //Dependency Injected
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		
		Laptop laptop1 = dependency.createLaptop();
		laptop1.setBrand("Dell");
		laptop1.setDescription("It is dell laptop.");
		laptop1.setPerson(person);
		
		Laptop laptop2 = dependency.createLaptop();
		laptop2.setBrand("System76");
		laptop2.setDescription("This is awesome!");
		laptop2.setPerson(person);
		
		Laptop laptop3 = dependency.createLaptop();
		laptop3.setBrand("MacBook");
		laptop3.setDescription("This is just shit!");
		laptop3.setPerson(person);
		
		LaptopDao laptopDao = dependency.createLaptopDao(); //Dependency Injected
		laptopDao.save(laptop1);
		laptopDao.save(laptop2);
		laptopDao.save(laptop3);
		
		List<Laptop> laptops = laptopDao.findAll();
		for (Laptop laptop: laptops) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(); //To reduce heap size
			sb.append("Laptop [id="+laptop.getId());
			sb.append(", brand="+laptop.getBrand());
			sb.append(", description="+laptop.getDescription());
			sb.append(", person_id="+laptop.getPerson().getId()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		Laptop laptop = laptopDao.findOne(1); //get laptop at id=1;
		//System.out.println(laptop.getBrand()); //This can result in LazyInitializationException.
		LaptopDto dto = dependency.createLaptopDto(laptop); //To avoid LazyInitializationException, use DTO
		System.out.println(dto.getPerson());
		if (laptop!=null) {
			laptopDao.delete(laptop);
		}
	}
	
	private static void saveFromPersonEntity() {
		DependencyRegistry dependency = DependencyRegistry.getInstance(); //Dependency
		
		Person person = dependency.createPerson(); //Dependency Injected
		person.setFullName(new FullName("Dwayne", "Johnson"));
		person.setEmail("dwaynejohnson@gmail.com");
		person.setPhNo("+1549724440");
		
		Laptop laptop1 = dependency.createLaptop();
		laptop1.setBrand("Dell");
		laptop1.setDescription("It is dell laptop.");
		laptop1.setPerson(person);
		
		Laptop laptop2 = dependency.createLaptop();
		laptop2.setBrand("System76");
		laptop2.setDescription("This is awesome!");
		laptop2.setPerson(person);
		
		Laptop laptop3 = dependency.createLaptop();
		laptop3.setBrand("MacBook");
		laptop3.setDescription("This is just shit!");
		laptop3.setPerson(person);
		
		person.getLaptop().add(laptop1);
		person.getLaptop().add(laptop2);
		person.getLaptop().add(laptop3);
		
		PersonDao personDao = dependency.createPersonDao(); //Dependency Injected
		personDao.save(person);
		
		Person prn = personDao.findOne(1);
		PersonDto personDto = new PersonDto(prn);
		System.out.println(personDto.getLaptop().get(0).getBrand()); //Only Eager Type Will Work!
		//EAGER can reduce performance. So, using JOIN table is more efficient!
		if (prn!=null) {
			personDao.delete(prn);
		}
		
		/*
		LaptopDao laptopDao = dependency.createLaptopDao(); //Dependency Injected
		List<Laptop> laptops = laptopDao.findAll();
		for (Laptop laptop: laptops) {
			//String is immutable in java.
			StringBuilder sb=dependency.createStringBuilder(); //To reduce heap size
			sb.append("Laptop [id="+laptop.getId());
			sb.append(", brand="+laptop.getBrand());
			sb.append(", description="+laptop.getDescription());
			sb.append(", person_id="+laptop.getPerson().getId()+"]");
			System.out.println(sb.toString()+" have been inserted!");
		}
		
		Laptop laptop = laptopDao.findOne(1); //get laptop at id=1;
		//System.out.println(laptop.getBrand()); //This will make LazyInitializationException.
		LaptopDto dto = dependency.createLaptopDto(laptop); //To avoid LazyInitializationException, use DTO
		System.out.println(dto.getPerson());
		if (laptop!=null) {
			laptopDao.delete(laptop);
		}
		*/
	}
}
