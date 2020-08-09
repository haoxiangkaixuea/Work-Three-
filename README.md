# Work-Three-Week
## LitePal

配置build.gradle：资源库去找,目前版本为3.1.1

配置litepal.xml：

数据库名字

```
<dbname value="demo" />
版本号
<version value="1" />
数据库表（内容包名+类名）
<list>
    <mapping class="cn.edu.scujcc.workthreeweek.data.model.BookModel" />
    <mapping class="cn.edu.scujcc.workthreeweek.data.model.CategroyModel" />
</list>
```

- 创建数据库：

  LitePal.getDatabase();

- 添加数据

  创建数据库类的实例，然后依次添加数据，最后用save方法保存

- 更新数据

  使用updateAll()

  ```
  bookModel.setPrice(10.9);
  bookModel.save();
  bookModel.updateAll("name= ? and author =?", "红楼梦", "曹雪芹");
  ```

- 删除数据

  使用deleteAll()

  ```
  LitePal.deleteAll(BookModel.class, "price< ?", "40");
  ```

- 查询数据

  ```
  List<BookModel> firstBook = (List<BookModel>) LitePal.findFirst(BookModel.class);
  ```

## android中的异步处理

线程，可以看作是进程的实体，CPU调度资源的基本单位。本质上是一串命令（也就是程序代码），执行线程可以理解为把命令交给操作系统去执行。
Android中主线程也叫UI线程。Android3.0以后，系统要求网络访问必须在子线程中进行。

- **主线程：**又叫UI线程，由ActivityThread管理

> 作用：运行四大组件，和用户交互以及更新UI。

- **子线程**

> 作用：处理耗时操作，比如网络请求，复杂计算等。

### 1、Handle

基本的线程，可以做一些简单的操作，经常配合Handler使用。

### 2、.AsyncTask

轻量级的异步操作类，AsyncTask 封装了Thread和Handler, ，方便更新UI。但是AsyncTask并不适合进行特别耗时的后台任务,对于特别耗时的后台任务来说建议使用线程池。

AsyncTask是一个抽象类，所以如果我们想使用它，就必须要创建一个子类去继承它。在继承时我们可以为AsyncTask类指定三个泛型参数，这三个参数的用途如下：

1、Params

在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。

2、Progress

后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。

3、Result

当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。

### 3、Intentservice

封装了HandlerThread和一个Handler，是HandlerThread的具体使用，由于属于Service，若以比单纯的线程优先级更高。

1. IntentService是一种特殊的服务, 他继承自Service并且他是一个抽象类.

2. IntentService用于后台耗时任务, 任务执行结束后自动停止.

3. 由于它是一个服务因此有着比普通Thread更高的优先级,不容易被系统杀死.因此他适合执行一些优先级较高的后台任务


#### 线程池





## contentProvider

ContentProvider是Android4大组件之一，我们平时使用的机会可能比较少。其底层通过Binder进行数据共享。如果我们要对第三方应用提供数据，可以考虑使用ContentProvider实现。



![image-20200809110346441](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200809110346441.png)



- ### 增删查改

  ##### 例如：查

  1. 通过Context获取ContentResolver

  2. 调用它的query方法

  3. 通过ContentResolver 进行uri匹配

     ContentResolver resolver = getContentResolver(); 

     Cursor cursor = resolver.query(Uri.parse(""),null,null,null,null); 

     if(cursor != null){

     ​    while (cursor.moveToNext()){    

     ​    Log.d("tag","query result "+cursor.getColumnNames());  

       }    

     cursor.close(); 

     }

     ```dart
     // 外部进程向 ContentProvider 中添加数据
     public Uri insert(Uri uri, ContentValues values)　 
     
     // 外部进程 删除 ContentProvider 中的数据
     public int delete(Uri uri, String selection, String[] selectionArgs)
     
     // 外部进程更新 ContentProvider 中的数据
     public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)　 
     
     // 外部应用 获取 ContentProvider 中的数据
     public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
     ```

- ### 与其他的ContentProvider通信

要实现与其他的ContentProvider通信首先要查找到对应的ContentProvider进行匹配。android中ContenProvider借助ContentResolver通过Uri与其他的ContentProvider进行匹配通信。

- 步骤说明：

  1. 创建数据库类

  2. 自定义 `ContentProvider` 类

  3. 注册 创建的 `ContentProvider`类

  4. 进程内访问 `ContentProvider`的数据

     

##### 实现自己的ContentProvider

继承ContentProvider,实现对应的方法。在manifest中声明。

实现自的ContentProvider需要继承Android系统的ContentProvider然后实现下面的几个方法。

- onCreate()
- query()
- getType()
- insert()
- delete()
- update()

需要注意的是除了onCreate()其他的方法都运行在binder线程池。

然后在Manifest中声明对应的contentProvider即可。

##### ContentResolver如何返回Cursor对象

在跨进程的情况下返回的是CursorToBulkCursorAdaptor对象，其实质是借助Binder的跨进程传输能力，在ContentProvider进程中序列化，在调用程序中反序列化。

##### 为什么要用 ContentProvider ?它和 sql 的实现上有什么差别?

- `ContentProvider` 屏蔽了数据存储的细节 , 内部实现对用户完全透明 , 用户只需要关心操作数据的 `uri` 就可以了, `ContentProvider` 可以实现不同 `app`之间 共享。
- `Sql` 也有增删改查的方法, 但是 `sql` 只能查询本应用下的数据库。
- 而 `ContentProvider` 还可以去增删改查本地文件. `xml` 文件的读取等。

![img](https://upload-images.jianshu.io/upload_images/16244318-1cefc2928df2bceb.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)



##### `android:exported` 属性，和`android:multiprocess`属性

- `android:exported` 属性非常重要。这个属性用于指示该服务是否能够被其他应用程序组件调用或跟它交互。
- 如果设置为 `true`，则能够被调用或交互，否则不能。
- 设置为 `false` 时，只有同一个应用程序的组件或带有相同用户 `ID` 的应用程序才能启动或绑定该服务。
- 对于需要开放的组件应设置合理的权限，如果只需要对同一个签名的其它应用开放 `ContentProvider` ，则可以设置 `signature` 级别的权限。

- `ContentProvider` 可以在 `AndroidManifest.xml` 中配置一个叫做 `android:multiprocess` 的属性，默认值是 false ，表示 ContentProvider 是单例的。
- 无论哪个客户端应用的访问都将是同一个 `ContentProvider` 对象，如果设为 `true` ，系统会为每一个访问该 `ContentProvider` 的进程创建一个实例。
