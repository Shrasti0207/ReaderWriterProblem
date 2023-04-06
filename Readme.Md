## Writer-Preference Reader-Writer Problem
This code implements a solution to the classic reader-writer problem using the writer-preference approach. <br>
The code is written in Scala and uses the java.util.concurrent.locks.ReentrantReadWriteLock class to synchronize access to a shared variable among multiple threads.<br>
The WriterPreferenceReaderWriter class defines two methods, read() and write(), that access the shared variable using the ReentrantReadWriteLock.<br>
This code will be runnable with the help of the sbt and intellij.