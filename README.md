# Small Memory Footprint Java Date Time Library

The date and time classes of `java.time` use several instance fields to represent the year, month, etc. and that is heavy-weight memory wise. This library stores time as a single `long` and is a classic trade-off between memory and computation time. However, the compact representation can be more cache friendly depending on the application and tuning. It's also beneficial in applications that need to keep a large number of date time instances in memory.
See below section for the performance tests.

The primary use case for this library is in financial applications where memory and latency are of high importance. Typically that would be in the price flow where data rates are high.

**Note:** The implementation of the date related fields in this library is using the same computations as are used in the method `ofEpochDay` in `java.time.LocalDate`.

## Guide
There are two flavors of the library: Using it as utility methods (static methods) or as plan objects.

### Using Utility Methods
First do a static import:

    import static systems.speedy.lmf.time.UnixTimestampConverter.*;

Then use any of the utility methods, e.g. `yearOf` to extract the year part of the timestamp:

    int year = yearOf(unixTimeStampInMillis);

### Using Objects

First create a `UTCDateTime`:

    long unixTimeStampInMillis = // Get the timestamp, e.g. System.currentTimeMillis()
    UTCDateTime udt            = new UTCDateTime(unixTimeStampInMillis);

Then use one of the getter methods, e.g.:

    int year = udt.getYear();

# Performance Tests
A simple set of performance tests can be found in `src/perf/java`. There are three different tests following the same template:

- `PerfTest1.java`: Using the static methods of this library for conversion.
- `PerfTest2.java`: Using the instance methods of this library for conversion.
- `PerfTest3.java`: Using the `java.time.LocalDateTime` instance for conversion.

The following results were achieved on a 4 GB DDR3, 2.13 GHz Intel Core 2 Duo MacBook Air with OSX 10.9.5:

- `PerfTest1.java`: 12.0 ms or 60 ns per converted timestamp. (Std. dev. of 0.0, no GC during actual test runs.)
- `PerfTest2.java`: 12.0 ms or 60 ns per converted timestamp. (Std. dev. of 0.0, no GC during actual test runs.)
- `PerfTest3.java`: 23.4 ms or 117 ns per converted timestamp. (Std. dev. of 5.4, no GC during actual test runs.)

Not surprisingly, the first two have identical performance as the JVM will generate the same JIT code, for both usages of the library (Escape Analysis will reveal that there's no need for allocating an instance). However, it is surprising, that this library is almost twice as fast in the test compared to the implementation in `java.time.LocalDateTime`. Without further investigation, it's likely because this library allocates less memory and therefore is more cache friendly.

**Note:** These test results cannot be generalized to any program. Please conduct actual performance tests of your application before deciding whether to use this library.