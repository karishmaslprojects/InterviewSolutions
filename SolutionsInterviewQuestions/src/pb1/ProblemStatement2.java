package pb1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class ProblemStatement2 {
	public static void main(String[] args) {
		ArrayList<Event> input=new ArrayList<>();
		input.add(new Event(1,100,5,0,10));
		input.add(new Event(1,100,5,2,5));
		input.add(new Event(1,100,5,2,3));
		input.add(new Event(1,100,5,0,7));
		input.add(new Event(1,100,6,0,7));
		
		//The data Structure we can use is map of maps
		//We can use LinkedHashMap to maintain the insertion order
		//We can calculate key as a string for the outermap
		//for the innerMap key is subcategoryId
		//group by date,userId,categoryId and then group by subcategoryID and apply computation for wildcard 
		LinkedHashMap<EventKey,LinkedHashMap<Integer,Integer>> dataStructure=new LinkedHashMap<>();
		//Key Generation for the LinkedHashMap can be String key = date+","+userId+","+categoryId
		//We can also create an object for the Event Key
				
		//Step1
		//Group by 1 the key of date,userId,categoryId -> (Key Computed as String , else you can create a new object class) 
		//Preserving Insertion order so using LinkedHashMap
		Map<EventKey,List<Event>> map1=input.stream().collect(
		Collectors.groupingBy(e->new EventKey(e.getDate(),e.getUserId(),e.getCategoryId()),LinkedHashMap::new,Collectors.toList()));
		for(Map.Entry<EventKey,List<Event>> mapEntry:map1.entrySet())
		{
			System.out.println(mapEntry.getKey()+" -- "+mapEntry.getValue());
		}
		
		for(Map.Entry<EventKey,List<Event>> mapEntry:map1.entrySet())	
		{
			LinkedHashMap<Integer,Integer> summap=new LinkedHashMap<>();
			int firstSubCategory=0;
			int wildCardSum=0;
 			//Get events belonging to particular date,userId,categoryId
			List<Event> alEvents=mapEntry.getValue();
			for(Event e:alEvents) 
 			{
 				if(e.subcategoryId!=0) 
 				{
 					summap.put(e.subcategoryId,summap.getOrDefault(e.subcategoryId, 0)+e.getValue());
 					if(firstSubCategory==0)
 					{
 						firstSubCategory=e.subcategoryId;
 						
 					}
 					//wildcard exists 
 					//and specific subcategory !=0 came in for the first time
 					if(wildCardSum!=0 && firstSubCategory!=0)
 					{
 						summap.put(firstSubCategory,summap.getOrDefault(firstSubCategory, 0)+wildCardSum);
 						wildCardSum=0;	
 					}
 				}
 				else 
 				{
 					//keep wildcard as it is 
 					wildCardSum+=e.getValue();
 					//merge the wildcard with the first one
 					if(firstSubCategory!=0)
 					{
 						summap.put(firstSubCategory,summap.getOrDefault(firstSubCategory, 0)+e.getValue());
 						wildCardSum=0;
 					}
 				}
			}
			//At the end check if wildcard was merged
			//If no create a new entry for subcategoryId=0
 			if(firstSubCategory==0)
			{
				summap.put(0,wildCardSum);
				wildCardSum=0;
			}
 			if(alEvents.size()>0)
			{
 				Event e = alEvents.get(0);
 				dataStructure.put(new EventKey(e.getDate(),e.getUserId(),e.getCategoryId()), summap);
 			}

		}
		
		System.out.println("Get the Result in the List");
		List<Event> result=new ArrayList<>();
		for(Entry<EventKey, LinkedHashMap<Integer, Integer>> mapEntry:dataStructure.entrySet())
		{
			EventKey ekey=mapEntry.getKey();
			Integer date=ekey.getDate();
			Integer userId=ekey.getUserId();
			Integer categoryId=ekey.getCategoryId();
			Map<Integer,Integer> innerMap=mapEntry.getValue();
			
			for(Map.Entry<Integer,Integer> mapEntry1:innerMap.entrySet())
			{
				Integer subCategoryId=mapEntry1.getKey();
				Event e=new Event(date,userId,categoryId,subCategoryId,mapEntry1.getValue());
				result.add(e);
			}	
		}
		result.forEach(System.out::println);
		
	}

}
