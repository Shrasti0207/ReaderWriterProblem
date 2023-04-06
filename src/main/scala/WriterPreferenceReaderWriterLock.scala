package com.knoldus
import java.util.concurrent.locks.ReentrantReadWriteLock

class WriterPreferenceReaderWriterLock {
  // Create a new instance of ReentrantReadWriteLock with writer preference
  private val lock = new ReentrantReadWriteLock(true)

  // Define a method for reading with a parameter block of type T which returns Unit
  def read[T](block: => T): Unit = {
    // Acquire the read lock
    val readLock = lock.readLock()
    readLock.lock()
    try {
      block
    } finally {
      // Release the read lock
      readLock.unlock()
      println("Reader thread unlocked")
    }
  }

  def write[T](block: => T): Unit = {
    // Acquire the write lock
    val writerLock = lock.writeLock()
    writerLock.lock()
    try {
      block
    } finally {
      // Release the write lock
      writerLock.unlock()
      println("Writer thread unlocked")
    }
  }
}