





#Fetch aquariums

```
curl --request POST \
  --url http://localhost:8080/rest/aquariums \
  --header 'Content-Type: text/plain' \
  --data '{
	 	aquarium(id: 2) {
	    id
			glassType  
			volume
			volumeUnit
			shape  
			fishes {
				fishCount
				fish { 
					id
					species
					color
				}
			
			}
	 }
	
}'


```


#Make an Aquarium 

```
curl --request POST \
  --url http://localhost:8080/rest/aquariums \
  --header 'Content-Type: text/plain' \
  --data '
mutation 
{

makeAquarium( 
	glassType: "TUFFEND",
	volume: 20.9,
	volumeUnit: "GALLONS" ,
	shape: "HALF_MOON"
  ){
	 id
			glassType  
			volume
			volumeUnit
			shape  
			fishes {
				fishCount
				fish { 
					id
					species
					color
				}
			
			}
}

} '
```
#Add Fishes to acqua

```
mutation 
{
updateFish(
	id: 3,
	action: "ADD",
	fishes :[
		{			 
			fishId: 3,
			count: 3
		}
	]
  ){
	id
	glassType
	volume
	volumeUnit
	 fishes {
				fishCount
                fish { 
                    id
                    species
                    color
					numberFins
                }
			}
}


} 
```

#Make an Aquarium 

```
mutation 
{
updateFish(
	id: 3,
	action: "REMOVE",
	fishes :[
		{			 
			fishId: 3,
			count: 3
		}
	]
  ){
	id
	glassType
	volume
	volumeUnit
	 fishes {
				fishCount
                fish { 
                    id
                    species
                    color
					numberFins
                }
			}
}


} 
```


