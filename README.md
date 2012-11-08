diversify
=========

diversify european project development

To play with this project, just add the following dependencies to your project.
    <dependency>
      <groupId>fr.irisa.diversify</groupId>
  <artifactId>annotations-framework</artifactId>
  <version>0.0.1-SNAPSHOT</version>
    </dependency>


and add the plugin in your pom.xml


   <build>
        <plugins>
            <plugin>
				  <groupId>fr.irisa.diversify</groupId>
 					 <artifactId>mojo-mutator-maven-plugins</artifactId>
					  <version>0.0.1-SNAPSHOT</version>
					  <executions>					      
					      <execution> 
					          <goals>
					              <goal>
					                  generate-sources
					              </goal>
					          </goals>
					      </execution>
					  </executions>
            </plugin>
        </plugins>
    </build>


and place the annotations on the methods you want to modify.

See the examples project for more informations. 


