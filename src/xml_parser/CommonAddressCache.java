package xml_parser;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.File;
import xml_parser.Address;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.Node;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import xml_parser.database;
import xml_parser.cache;

public class CommonAddressCache {
	
	public static synchronized Address  createAddress(List<String> streets,String city, String state, String postalCode,String countryCode) 
	{

		StringBuffer street = new StringBuffer();
		int streetSize = streets.size();
		for (int i = 0; i < streetSize; i++) {
			street.append(streets.get(i));
			
		}
		Address address = new Address(city, state,  postalCode,  countryCode, street.toString());
		address.setLines(street.toString());
		address.setCity(city);
		address.setState(state);
		address.setPostalCode(postalCode);
		address.setCountry(countryCode);
		database d =new database();
		d.store(street.toString(),city, state,  postalCode,  countryCode);
		street=null;
		return address;
	}

	public static Address getEmployeeById(ArrayList<String> id) {
		System.out.println("--Executing getAddressById--");
		database d=new database();
		String p=d.search(id.get(0),id.get(1),id.get(2),id.get(3),id.get(4));
		if(p == null)
		{
			System.out.println("cmg here");
			d.store(id.get(0),id.get(1),id.get(2),id.get(3),id.get(4));
			Address emp2 = new Address(id.get(0),id.get(1),id.get(2),id.get(3),id.get(4));
			return emp2;
		}
		else
		{
		String arrOfStr[] = p.split(",");
		Address emp1 = new Address(arrOfStr[0],arrOfStr[1],arrOfStr[2],arrOfStr[3],arrOfStr[4]);
		
		return emp1;
		}
	   }
	
	private static LoadingCache<ArrayList<String>, Address> empCache;
    static {
	empCache = CacheBuilder.newBuilder()
	       .maximumSize(100)
	       .expireAfterWrite(10, TimeUnit.MINUTES)
	       .build(
	           new CacheLoader<ArrayList<String>, Address>() {
        		@Override
			public Address load(ArrayList<String> id) throws Exception {
				return getEmployeeById(id);
			}
	           }
	       );
    }
    
    public static LoadingCache<ArrayList<String>, Address> getLoadingCache() {
    	return empCache;
        }
	
	public synchronized Address  SearchDB(String lines, String city, String state, String postalCode, String countryCode) {
		//Address address = null; 
		System.out.println("--Searching--");
		database d =new database();
		cache cacheDemo = new cache();
		Address address = new Address(city, state,  postalCode,  countryCode, lines);
		HashMap<Integer,Address> map = new HashMap<>();
		ArrayList<String> singleAddress = new ArrayList<String>();
		singleAddress.add(lines);
		singleAddress.add(city);
		singleAddress.add(state);
		singleAddress.add(postalCode);
		singleAddress.add(countryCode);
		map.put(address.hashCode(),address);
		try {
			System.out.println(cacheDemo.getEmpUsingGuava(singleAddress));
			System.out.println(cacheDemo.getEmpUsingGuava(singleAddress));
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
// If address find in DB, set address and return. Otherwise,return null. The search address is slow, it approximately will take 5s.
		return address;
	}

	public Address getAddress(String lines, String city, String state, String postalCode, String countryCode) {
		Address address = null;
		// design your mechanism
		return address;
	}
	

	
}
