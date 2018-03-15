package xml_parser;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.google.common.cache.LoadingCache;
import xml_parser.Address;
import xml_parser.CommonAddressCache;

public class cache {

  public Address getEmpUsingGuava(ArrayList<String> singleAddress) throws ExecutionException {
	LoadingCache<ArrayList<String>, Address> empCache = CommonAddressCache.getLoadingCache();
	//System.out.println(empCache.stats());
	System.out.println("Cache Size:" + empCache.size());
	return empCache.get(singleAddress);
  }
  
}