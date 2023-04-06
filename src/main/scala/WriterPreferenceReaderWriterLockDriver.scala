package com.knoldus
import java.util.concurrent.locks.{ReadWriteLock, ReentrantReadWriteLock}

object WriterPreferenceReaderWriterLockDriver extends App {
  // Create a new instance of ReentrantReadWriteLock
  val lock: ReadWriteLock = new ReentrantReadWriteLock()
  // Initialize the shared resource to 0
  private var resource: Int = 0

  // Define reader1 thread
  private val readerOne = new Thread {
    override def run(): Unit = {
      // Acquire the read lock
      lock.readLock().lock()
      try {
        println(s"Reader One read: $resource")
        Thread.sleep(1000)
      } finally {
        // Release the read lock
        lock.readLock().unlock()
        println("Reader One unlocked")
      }
    }
  }
  // Define reader2 thread
  private val readerTwo = new Thread {
    override def run(): Unit = {
      lock.readLock().lock()
      try {
        println(s"Reader Two read : $resource")
        Thread.sleep(1000)
      } finally {
        lock.readLock().unlock()
        println("Reader Two unlocked")
      }
    }
  }
  // Define writer 1 thread
  private val writerOne = new Thread {
    override def run(): Unit = {
      lock.writeLock().lock()
      try {
        resource += 1
        println(s"Writer One write: $resource")
        Thread.sleep(500)
      } finally {
        lock.writeLock().unlock()
        println("Writer One unlocked")
      }
    }
  }
  // Define reader 3 thread
  private val readerThree = new Thread {
    override def run(): Unit = {
      lock.readLock().lock()
      try {
        println(s"Reader Three read : $resource")
        Thread.sleep(1000)
      } finally {
        lock.readLock().unlock()
        println("Reader Three unlocked")
      }
    }
  }
  // Define writer 2 thread
  private val writerTwo = new Thread {
    override def run(): Unit = {
      lock.writeLock().lock()
      try {
        resource += 1
        println(s"Writer Two write: $resource")
        Thread.sleep(500)
      } finally {
        lock.writeLock().unlock()
        println("Writer Two unlocked")
      }
    }
  }
  // Start all the threads
  writerOne.start()
  writerTwo.start()
  readerOne.start()
  readerTwo.start()
  readerThree.start()
  // Wait for all the threads to finish
  readerOne.join()
  readerTwo.join()
  writerOne.join()
  readerThree.join()
  writerTwo.join()
}
