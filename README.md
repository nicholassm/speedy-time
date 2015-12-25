# Small Memory Footprint Java Date Time Library

The date and time classes of `java.time` use several instance fields to represent the year, month, etc. and that is heavy-weight memory wise. This library stores time as a single `long` and is a classic trade-off between memory and computation time. However, the compact representation can be more cache friendly depending on the application and tuning. It's also beneficial in applications that need to keep a large number of date time instances in memory.

The primary use case for this library is in financial applications where memory and latency are of high importance. Typically that would be in the price flow where data rates are high.