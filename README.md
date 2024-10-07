# java-model-mapper
This is used to map one object type to another of the same parameters

## Example entity:
1. We have an entity SourceEntity containing parameters like id, name, email and address (Object of class SourceAddress)
2. We have an entity TargetEntity containing parameters like id, name, email and address (Object of class TargetAddress)
   
## Usage can be seen in the below given code:
SourceAddress sourceAddress = new SourceAddress("mother earth");
SourceEntity source = new SourceEntity(1, "Nikhil Sharma", "Nikhil@example.com", sourceAddress);
TargetEntity target = convertEntity(source, TargetEntity.class);
System.out.println("Source Entity: " + source);
System.out.println("Target Entity: " + target);
