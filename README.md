# fetch_android_test

## Libraries Used
RxJava  
Dagger2  
Retrofit2  
All the dependecies are present in build.gradle.  

## Description
1. I have used MVVM architecture to implement the application. ```DisplayItemsActivity``` handles the ui part of the application. ```DisplayItemsViewModel``` handles the data transformations and ```DisplayItemsRepository``` handles server calls.    
2. RxJava is used to handle server calls on background thread and can also perform further transformations using mao and flatmap operators.    
3. ```Single<Item>``` is used currently, since there was a single list of items. ```Observalble```, ```Flowable``` can be used in future applications involving multiple lists of items.   
4. Dagger2 is used for dependency injection to inject ```DisplayItemsRepository``` object in activity.     
5. Retorfit2 library is used for connection to the server. HttpLogging is done only in debug builds to improve performance in release builds.    
6. ```minifyEnabled``` is set to true in release builds to obfuscate, shrink and optimize.   
7. Basic error handling is done to avoid corrupt item name from server.

## Future Work
1. Handle continuous calls from server using Observable, Flowable etc.
2. Pagination by making continous calls to server.
3. Write Junit test cases.
4. Save items in Room database so that they can be persistent.
