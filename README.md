Hw5 : Social Network Analysis


__________Team 30 Jiyu Zhu(jiyuz) Xinqian Li(xinqianl) Zhiqi Xu(zhiqix)_________


###### 1. Three easy steps to use our framework GUI

__Step 1__ 
After launching the framework, you will see four input fields for user names to be analyzed. Firstly choose the social platform type like “Facebook” or “Twitter” then input the user name, after you have input all the user names you want to retrieve information from, you can click the “Get Data” button to get data of input user names from selected platforms. Also, you can click the “Clear All” button to clear all the input fields then try again.

__Step 2__  
The process of retrieving data may take a long time, that’s because we use the third party API to send HTTP requests, the more users we want to analyze, the more time we need to wait for the sending request and getting response process. 
Due to the authorization reason, some private user accounts can not be retrieved data from, so if you type this kind of user, you will get a warning message on the white information panel of the GUI. Of course, if you input an user name that does not exist, you will get the same warning message too. After the long waiting time, we will jump to the analysis plugin chosen page, in this page, you can choose the analysis plugins, then press the “Analyze” button to see the analysis results. Also, you can click the "Go Back"button to come back to the main menu to start over again.

__Step 3__ 
If you have chosen multiple analysis plugins, you will see the a tabbled panel at the top of the window with chart content and you can select the analysis type by clicking different tabs. You can click the "Back" button to go back to the choose analysis menu to choose other analysis types again or go back to the main menu to start over. Also, you can click the "Exit" button to end.


###### 2.  Framework introductions

__Overall__ 
FrameworkImpl class implements Framework interface. Framework can register data plugins and analysis plugins, let GUI set information
to it and get information from it, get data from data plugins, preprocess data and pass data to analysis plugins.  GUI class implements 
FrameworkListener interface and it subscribes to the Framework. If some changes take place in the framework, the framework will notify 
all the subscribed FrameworkListener objects to update, for example ask FrameworkListeners to draw the result panels.

__Methods and Fields in FrameworkImpl Explanation__

allAnalyzedData : ArrayList<AnalyzedData>	
\\AnalyzedData is a class to store user information that framework gets from data plugins and has two more fields from pre-processing, 
wordsCount and nonWordsCount. FrameworkImpl class has a field, an ArrayList of AnalyzedData called allAnalyzedData to store all the 
analyzed data of input users and this allAnalyzedData will be passed to analysis plugins.

analysisPlugins : HashMap<String, AnalysisPlugin>	
\\Key is analysis plugin’s name and value is corresponding analysis plugin. This hash map contains all types of registered analysis plugins.

dataPlugins : HashMap<String, DataPlugin>	
\\Key is data plugin’s name and value is corresponding data plugin. This hash map contains all types of registered data plugins.

namePlatformPairs : HashMap<String, String>	
\\Key is the name of input user name. Value is the platform name. User names and platform names are input in the first window of the GUI.

subscribedFrameworkListeners : ArrayList<FrameworkListener>	
\\A list of subscribed FrameworkListener. GUI implements FrameworkListener interface.

addAnalysisPlugin(AnalysisPlugin) 
\\Add analysis plugin to the framework.

addDataPlugin(DataPlugin)	
\\Add data plugin to the framework.

setAllAnalyzedData()	
\\A helper method for setNamePlatformPairs method. After user name and platform is input, each user’s data will be preprocess by “new 
AnalyzedData(UserData)”. Then the allAnalyzedData list is set up.

setNamePlatformPairs(HashMap<String, String>)	
\\Key is user name. Value is platform name. GUI fetches the information and passes it to framework. In this method, setAllAnalyzedData 
method is called.

setSelectedAnalysis(ArrayList<String>)	
\\The preprocessed allAnalyzedData list is passed to selected analysis plugins. The analysis result JPanels and user name are passed to 
notifyToDrawPanels(HashMap) method.

__Preprocess Data__
UserData is passed to AnalayzedData class. In the AnalyzedData class, besides the information in the UserData, words and non-words in all 
posts are classified and stored in the wordsCount and nonWordsCount hash maps. These attributes are used in all analysis plugins.

__Design Discussion__
We use observer pattern in the design. The framework is observer and framework listeners are observees. If any changes take place in the 
framework, framework will notify all subscribed framework listeners. The advantage is if there are many GUIs and some changes take place 
in one GUI, like some info is input, all GUIs will be updated. We also use a Framework interface to achieve information hiding. Clients 
won’t know the internal implementation of FrameworkImpl class. Data plugins implement a DataPlugin interface and Analysis plugins implement an AnalysisPlugin interface using strategy pattern which increases the extensibility. Different data plugins are adaptors which transfer user information from libraries to UserData.


###### 3.   What are our Data Plugins?

In our framework, we provide two DataPlugins, they are “Facebook” and “Twitter”. We can get information from these two platforms for specified users, the __DataPlugin interface__ offers the following methods:

public String getDataPluginName()
public userData getUserData(String userName)

The first method can return the name of the DataPlugin, thus telling the framework which DataPlugin is using or being called now. 
The second method can return an userData object by giving an username, if the user data is invalid, userData's errorMsg will not be null. The fields of userData class are as followings:

     private String userName;     // the user name typed in the input field
     private String userID;       // the primer key that distinguishes the user
     private String userLocation; // the location info of the user
     private List<Post> posts;    // the post array posted by the user
     private int followers;       // the number of the followers that follow the user
     private int followees;       // the number of the friends that the user follows
     private String platform;     // the platform that we retrieve the info of the user from

And for the Post class, it has fields:

     private String content;      // the text content of the post
     private Date date; // the date time that user posted this post

So after we call the getUserData method from the concreted DataPlugin, we will get the data and store it in a UserData object and return to the framework. The reason why we set the parameter of the getUserData to be single user name instead of an user name list is that if the process of getting data throws exceptions, we will exactly know which user name causes the problem.


###### 4. What are our Analysis Plugins?

In our framework, we provide three types of AnalysisPlugins, they are “Key Word Analysis”, “Positive Analysis” and “Connective Activity Analysis”.
The __AnalysisPlugin interface__ offers the following methods:

public String getName()
public JPanel analyzeData(List<AnalyzedData> analyzedDatas)

The first method can return the name of the AnalysisPlugin, thus telling the framework which AnalysisPlugin is using or being called now. The second method will transfer a list of AnalyzedData objects into the plugin and return a JPanel back to the framework. The length of the list is the number of users.

__Our three types of AnalysisPlugins__

(1) Key Word Analysis Plugin__ as the name indicates, this plugin will get and show on the panel the 9 most used words in all the posts from the user's account. The top 9 words won't include prepositions and articles. The larger the word appear on the panel, the more it appears in the posts.
(2) Positive/Negative Analysis__ We have a big word bank which has all the possible positive and negative words. For all the posts of the user, we will calculate the score for the positive and negative attitude of the posts.
(3) User World Interaction Counter__ For all the punctuations of the user's posts, we calculate the number of the "@","http://","#" and other to show the interaction of the user with the world.
