# Work-Three-Week
## LitePal

此项目为LitePalActivity

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

- ##### **主线程**

> 作用：运行四大组件，和用户交互以及更新UI。

- ##### **子线程**

> 作用：处理耗时操作，比如网络请求，复杂计算等。

### 1、Handler

此项目为HandlerActivity

基本的线程，可以做一些简单的操作，经常配合Handler使用。

 **1. Message**

   消息，理解为线程间通讯的数据单元。例如后台线程在处理数据完毕后需要更新UI，则可发送一条包含更新信息的Message给UI线程。

  **2. Message Queue**

   消息队列，用来存放通过Handler发布的消息，按照先进先出执行。

  **3. Handler**

   Handler是Message的主要处理者，负责将Message添加到消息队列以及对消息队列中的Message进行处理。

 **4. Looper**

   循环器，扮演Message Queue和Handler之间桥梁的角色，循环取出Message Queue里面的Message，并交付给相应的Handler进行处理。

  **5. 线程**

   UI thread 通常就是main thread，而Android启动程序时会替它建立一个Message Queue。

```java 
final Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
        switch(msg.what){
            case 1:
                text.setText("开启线程");
        }
    }
};
```


```java
private void doWork(final String url, final int id) {
    Thread thread = new Thread() {
        @Override
        public void run() {
            try {
                 Thread.sleep(100);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Message message = handler.obtainMessage();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    thread.start();
}
```
handler用sendMessage把支线程的请求消息发送给主线程，主线程接收后，用Loop.lopper把消息传回给支线程。

### 2、AsyncTask

此项目为AsyncTaskActivity

轻量级的异步操作类，AsyncTask 封装了Thread和Handler, ，方便更新UI。但是AsyncTask并不适合进行特别耗时的后台任务,对于特别耗时的后台任务来说建议使用线程池。

AsyncTask是一个抽象类，所以如果我们想使用它，就必须要创建一个子类去继承它。在继承时我们可以为AsyncTask类指定三个泛型参数，这三个参数的用途如下：

1、Params

在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。

2、Progress

后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。

3、Result

当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。

### 3、Intentservice

此项目为：IntentServiceActivity，MyIntentService

封装了HandlerThread和一个Handler，是HandlerThread的具体使用，由于属于Service，若以比单纯的线程优先级更高。

1. IntentService是一种特殊的服务, 他继承自Service并且他是一个抽象类.

2. IntentService用于后台耗时任务, 任务执行结束后自动停止.

3. 由于它是一个服务因此有着比普通Thread更高的优先级,不容易被系统杀死.因此他适合执行一些优先级较高的后台任务

## 线程池

使用线程池的好处：

> 1、重用线程池中的线程，避免因频繁创建和销毁线程造成的性能消耗。
> 2、更加有效的控制线程的最大并发数，防止线程过多抢占资源造成的系统阻塞。
> 3、对线程进行有效的管理。

##### 几种常见的线程池

###### 1、ThreadPoolExecutor 

创建基本线程池

创建线程池，主要是利用ThreadPoolExecutor这个类，而这个类有几种构造方法，其中参数最多的一种构造方法:

```java
  public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory) {
        ...
    }
```

corePoolSize: 该线程池中核心线程的数量。

maximumPoolSize：该线程池中最大线程数量。

keepAliveTime:非核心线程空闲时要等待下一个任务到来的时间。

unit:上面时间属性的单位。

workQueue:任务队列。

threadFactory:线程工厂，可用于设置线程名字等等。

**适用：执行很多短期异步的小程序或者负载较轻的服务器**

###### 2、FixedThreadPool 

(可重用固定线程数)

```java
final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
```

参数为核心线程数，只有核心线程，无非核心线程，并且阻塞队列无界。

**适用：执行长期的任务，性能好很多**

###### 3、CachedThreadPool 

(按需创建)

```java
final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
```

特点：没有核心线程，只有非核心线程，并且每个非核心线程空闲等待的时间为60s，采用SynchronousQueue队列。

**适用：周期性执行任务的场景**

###### 4、SingleThreadPool

(单个核线的fixed)

```java
final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
```

**适用：一个任务一个任务执行的场景**

###### 5、ScheduledThreadPool

(定时延时执行)

```java
final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
```

###### 6、自定义的PriorityThreadPool

(队列中有优先级比较的线程池)

```java
final ExecutorService priorityThreadPool = new ThreadPoolExecutor(3,3,0, TimeUnit.SECONDS,new PriorityBlockingQueue<Runnable>());
```

#### 线程池其它方法：

```java
1.shutDown()  关闭线程池，不影响已经提交的任务

2.shutDownNow() 关闭线程池，并尝试去终止正在执行的线程

3.allowCoreThreadTimeOut(boolean value) 允许核心线程闲置超时时被回收

4.submit 一般情况下我们使用execute来提交任务，但是有时候可能也会用到submit，使用submit的好处是submit有返回值。

5.beforeExecute() - 任务执行前执行的方法

6.afterExecute() -任务执行结束后执行的方法

7.terminated() -线程池关闭后执行的方法
```

一、线程池： 

提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。

| 二、线程池的体系结构： |                                                              |
| ---------------------- | ------------------------------------------------------------ |
|                        | java.util.concurrent.Executor : 负责线程的使用与调度的根接口 |
|                        | ExecutorService 子接口: 线程池的主要接口                     |
|                        | ThreadPoolExecutor 线程池的实现类                            |
|                        | ScheduledExecutorService 子接口：负责线程的调度              |
|                        | ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService |

| 三、工具类 : Executors |                                                              |
| ---------------------- | ------------------------------------------------------------ |
|                        | ExecutorService newFixedThreadPool() : 创建固定大小的线程池  |
|                        | ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。 |
|                        | ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程 |
|                        | ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。 |

## ContentProvider

ContentProvider是Android4大组件之一，我们平时使用的机会可能比较少。其底层通过Binder进行数据共享。如果我们要对第三方应用提供数据，可以考虑使用ContentProvider实现

### 增删查改

例如：查

1. 通过Context获取ContentResolver

2. 调用它的query方法

3. 通过ContentResolver 进行uri匹配

```java
public Cursor query(Uri uri, String[] projection, String selection,
                             String[] selectionArgs, String sortOrder) {
             db = dbHelper.getReadableDatabase();
             Cursor cursor = null;
             switch (URI_MATCHER.match(uri)) {
                 case BOOK_CODE:
                     cursor = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                     break;
                 default:
                     break;       
             }
          return cursor;
     }
```
```java
     
     // 外部进程向 ContentProvider 中添加数据
    public Uri insert(Uri uri, ContentValues values)　 
     
    // 外部进程 删除 ContentProvider 中的数据
     public int delete(Uri uri, String selection, String[] selectionArgs)
     
     // 外部进程更新 ContentProvider 中的数据
     public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)　 
     
     // 外部应用 获取 ContentProvider 中的数据
     public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
```

### ContentProvider通信



此项目中为：DBHelper数据库类，DataContentProvider内容提供器，DataContentActivity进程

要实现与其他的ContentProvider通信首先要查找到对应的ContentProvider进行匹配。android中ContenProvider借助ContentResolver通过Uri与其他的ContentProvider进行匹配通信。

- 步骤说明：

  1. 创建数据库类

  2. 自定义 `ContentProvider` 类

  3. 注册 创建的 `ContentProvider`类

  4. 进程内访问 `ContentProvider`的数据


- ##### 实现自己的ContentProvider

继承ContentProvider,实现对应的方法。在manifest中声明。

实现自的ContentProvider需要继承Android系统的ContentProvider然后实现下面的几个方法。

1. onCreate()
2. query()
3. getType()
4. insert()
5. delete()
6. update()

需要注意的是除了onCreate()其他的方法都运行在binder线程池。

然后在Manifest中声明对应的ContentProvider即可。

- ##### ContentResolver如何返回Cursor对象


在跨进程的情况下返回的是CursorToBulkCursorAdaptor对象，其实质是借助Binder的跨进程传输能力，在ContentProvider进程中序列化，在调用程序中反序列化。

- ##### 为什么要用 ContentProvider ?它和 sql 的实现上有什么差别?


1. `ContentProvider` 屏蔽了数据存储的细节 , 内部实现对用户完全透明 , 用户只需要关心操作数据的 `uri` 就可以了, `ContentProvider` 可以实现不同 `app`之间 共享。
2. `Sql` 也有增删改查的方法, 但是 `sql` 只能查询本应用下的数据库。
3. 而 `ContentProvider` 还可以去增删改查本地文件. `xml` 文件的读取等。

- 
  ##### `android:exported` 属性，和`android:multiprocess`属性


1. `android:exported` 属性非常重要。这个属性用于指示该服务是否能够被其他应用程序组件调用或跟它交互。
2. 如果设置为 `true`，则能够被调用或交互，否则不能。
3. 设置为 `false` 时，只有同一个应用程序的组件或带有相同用户 `ID` 的应用程序才能启动或绑定该服务。
4. 对于需要开放的组件应设置合理的权限，如果只需要对同一个签名的其它应用开放 `ContentProvider` ，则可以设置 `signature` 级别的权限。

5. `ContentProvider` 可以在 `AndroidManifest.xml` 中配置一个叫做 `android:multiprocess` 的属性，默认值是 false ，表示 ContentProvider 是单例的。
6. 无论哪个客户端应用的访问都将是同一个 `ContentProvider` 对象，如果设为 `true` ，系统会为每一个访问该 `ContentProvider` 的进程创建一个实例。
