This project is to check the behavior of Infinispan's Grouping API.

Three server nodes are built, and when `Main` is executed, the following logs are output on each node.

# Node 1
```
*************
Primary segments
3, 4, 5, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 165, 166, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238

JavaParentKey{ parentId=0 } : SegmentInfo{ calculatedSegment=35, storedSegment=72 }

Backup segments
0, 1, 2, 6, 7, 8, 9, 12, 13, 14, 15, 33, 34, 35, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 149, 164, 167, 183, 185, 186, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 239, 240, 241, 242, 243, 244, 254, 255

JavaParentKey{ parentId=1 } : SegmentInfo{ calculatedSegment=137, storedSegment=99 }

*************
Primary segments
3, 4, 5, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 165, 166, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238

JavaChildKey{ parentId=0, branchNumber=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=207 }
JavaChildKey{ parentId=0, branchNumber=3 } : SegmentInfo{ calculatedSegment=200, storedSegment=54 }
JavaChildKey{ parentId=1, branchNumber=0 } : SegmentInfo{ calculatedSegment=26, storedSegment=3 }
JavaChildKey{ parentId=1, branchNumber=3 } : SegmentInfo{ calculatedSegment=173, storedSegment=29 }
JavaChildKey{ parentId=1, branchNumber=5 } : SegmentInfo{ calculatedSegment=205, storedSegment=229 }
JavaChildKey{ parentId=1, branchNumber=6 } : SegmentInfo{ calculatedSegment=142, storedSegment=19 }
JavaChildKey{ parentId=1, branchNumber=7 } : SegmentInfo{ calculatedSegment=114, storedSegment=209 }
JavaChildKey{ parentId=2, branchNumber=2 } : SegmentInfo{ calculatedSegment=138, storedSegment=66 }
JavaChildKey{ parentId=2, branchNumber=3 } : SegmentInfo{ calculatedSegment=128, storedSegment=48 }
JavaChildKey{ parentId=2, branchNumber=8 } : SegmentInfo{ calculatedSegment=244, storedSegment=50 }

Backup segments
0, 1, 2, 6, 7, 8, 9, 12, 13, 14, 15, 33, 34, 35, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 149, 164, 167, 183, 185, 186, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 239, 240, 241, 242, 243, 244, 254, 255

JavaChildKey{ parentId=0, branchNumber=1 } : SegmentInfo{ calculatedSegment=202, storedSegment=78 }
JavaChildKey{ parentId=0, branchNumber=6 } : SegmentInfo{ calculatedSegment=174, storedSegment=60 }
JavaChildKey{ parentId=0, branchNumber=7 } : SegmentInfo{ calculatedSegment=241, storedSegment=15 }
JavaChildKey{ parentId=1, branchNumber=4 } : SegmentInfo{ calculatedSegment=218, storedSegment=85 }
JavaChildKey{ parentId=1, branchNumber=8 } : SegmentInfo{ calculatedSegment=255, storedSegment=186 }
JavaChildKey{ parentId=2, branchNumber=4 } : SegmentInfo{ calculatedSegment=181, storedSegment=219 }
JavaChildKey{ parentId=2, branchNumber=7 } : SegmentInfo{ calculatedSegment=31, storedSegment=144 }

*************
Primary segments
3, 4, 5, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 165, 166, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238

ProtoBufParentKey{ parentId=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=5 }

Backup segments
0, 1, 2, 6, 7, 8, 9, 12, 13, 14, 15, 33, 34, 35, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 149, 164, 167, 183, 185, 186, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 239, 240, 241, 242, 243, 244, 254, 255

ProtoBufParentKey{ parentId=1 } : SegmentInfo{ calculatedSegment=33, storedSegment=52 }

*************
Primary segments
3, 4, 5, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 165, 166, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238

ProtoBufChildKey{ parentId=0, branchNumber=3 } : SegmentInfo{ calculatedSegment=116, storedSegment=64 }
ProtoBufChildKey{ parentId=0, branchNumber=4 } : SegmentInfo{ calculatedSegment=116, storedSegment=71 }
ProtoBufChildKey{ parentId=0, branchNumber=6 } : SegmentInfo{ calculatedSegment=116, storedSegment=62 }
ProtoBufChildKey{ parentId=0, branchNumber=8 } : SegmentInfo{ calculatedSegment=116, storedSegment=74 }
ProtoBufChildKey{ parentId=1, branchNumber=0 } : SegmentInfo{ calculatedSegment=33, storedSegment=226 }
ProtoBufChildKey{ parentId=1, branchNumber=3 } : SegmentInfo{ calculatedSegment=33, storedSegment=64 }
ProtoBufChildKey{ parentId=1, branchNumber=4 } : SegmentInfo{ calculatedSegment=33, storedSegment=53 }
ProtoBufChildKey{ parentId=1, branchNumber=5 } : SegmentInfo{ calculatedSegment=33, storedSegment=218 }
ProtoBufChildKey{ parentId=1, branchNumber=6 } : SegmentInfo{ calculatedSegment=33, storedSegment=29 }
ProtoBufChildKey{ parentId=2, branchNumber=6 } : SegmentInfo{ calculatedSegment=155, storedSegment=25 }
ProtoBufChildKey{ parentId=2, branchNumber=8 } : SegmentInfo{ calculatedSegment=155, storedSegment=3 }

Backup segments
0, 1, 2, 6, 7, 8, 9, 12, 13, 14, 15, 33, 34, 35, 37, 38, 40, 41, 42, 43, 44, 45, 46, 47, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 149, 164, 167, 183, 185, 186, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 239, 240, 241, 242, 243, 244, 254, 255

ProtoBufChildKey{ parentId=0, branchNumber=1 } : SegmentInfo{ calculatedSegment=116, storedSegment=40 }
ProtoBufChildKey{ parentId=0, branchNumber=2 } : SegmentInfo{ calculatedSegment=116, storedSegment=223 }
ProtoBufChildKey{ parentId=1, branchNumber=7 } : SegmentInfo{ calculatedSegment=33, storedSegment=241 }
ProtoBufChildKey{ parentId=2, branchNumber=1 } : SegmentInfo{ calculatedSegment=155, storedSegment=96 }
ProtoBufChildKey{ parentId=2, branchNumber=3 } : SegmentInfo{ calculatedSegment=155, storedSegment=9 }
ProtoBufChildKey{ parentId=2, branchNumber=5 } : SegmentInfo{ calculatedSegment=155, storedSegment=94 }
```

# Node 2

```
*************
Primary segments
12, 13, 14, 15, 40, 45, 46, 47, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251


Backup segments
10, 11, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 67, 68, 69, 70, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 168, 178, 179, 180, 181, 216, 217, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 252, 253

JavaParentKey{ parentId=2 } : SegmentInfo{ calculatedSegment=206, storedSegment=121 }

*************
Primary segments
12, 13, 14, 15, 40, 45, 46, 47, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251

JavaChildKey{ parentId=0, branchNumber=0 } : SegmentInfo{ calculatedSegment=46, storedSegment=105 }
JavaChildKey{ parentId=0, branchNumber=5 } : SegmentInfo{ calculatedSegment=203, storedSegment=104 }
JavaChildKey{ parentId=0, branchNumber=7 } : SegmentInfo{ calculatedSegment=241, storedSegment=15 }
JavaChildKey{ parentId=0, branchNumber=8 } : SegmentInfo{ calculatedSegment=35, storedSegment=161 }
JavaChildKey{ parentId=1, branchNumber=8 } : SegmentInfo{ calculatedSegment=255, storedSegment=186 }
JavaChildKey{ parentId=2, branchNumber=0 } : SegmentInfo{ calculatedSegment=181, storedSegment=171 }
JavaChildKey{ parentId=2, branchNumber=5 } : SegmentInfo{ calculatedSegment=231, storedSegment=157 }
JavaChildKey{ parentId=2, branchNumber=6 } : SegmentInfo{ calculatedSegment=90, storedSegment=187 }
JavaChildKey{ parentId=2, branchNumber=7 } : SegmentInfo{ calculatedSegment=31, storedSegment=144 }

Backup segments
10, 11, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 67, 68, 69, 70, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 168, 178, 179, 180, 181, 216, 217, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 252, 253

JavaChildKey{ parentId=0, branchNumber=3 } : SegmentInfo{ calculatedSegment=200, storedSegment=54 }
JavaChildKey{ parentId=0, branchNumber=4 } : SegmentInfo{ calculatedSegment=159, storedSegment=253 }
JavaChildKey{ parentId=1, branchNumber=1 } : SegmentInfo{ calculatedSegment=19, storedSegment=124 }
JavaChildKey{ parentId=1, branchNumber=2 } : SegmentInfo{ calculatedSegment=214, storedSegment=122 }
JavaChildKey{ parentId=1, branchNumber=3 } : SegmentInfo{ calculatedSegment=173, storedSegment=29 }
JavaChildKey{ parentId=1, branchNumber=5 } : SegmentInfo{ calculatedSegment=205, storedSegment=229 }
JavaChildKey{ parentId=1, branchNumber=6 } : SegmentInfo{ calculatedSegment=142, storedSegment=19 }
JavaChildKey{ parentId=2, branchNumber=1 } : SegmentInfo{ calculatedSegment=203, storedSegment=123 }
JavaChildKey{ parentId=2, branchNumber=3 } : SegmentInfo{ calculatedSegment=128, storedSegment=48 }
JavaChildKey{ parentId=2, branchNumber=8 } : SegmentInfo{ calculatedSegment=244, storedSegment=50 }

*************
Primary segments
12, 13, 14, 15, 40, 45, 46, 47, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251

ProtoBufParentKey{ parentId=0 } : SegmentInfo{ calculatedSegment=116, storedSegment=187 }

Backup segments
10, 11, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 67, 68, 69, 70, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 168, 178, 179, 180, 181, 216, 217, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 252, 253


*************
Primary segments
12, 13, 14, 15, 40, 45, 46, 47, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 128, 129, 130, 131, 132, 133, 134, 135, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251

ProtoBufChildKey{ parentId=0, branchNumber=1 } : SegmentInfo{ calculatedSegment=116, storedSegment=40 }
ProtoBufChildKey{ parentId=0, branchNumber=5 } : SegmentInfo{ calculatedSegment=116, storedSegment=187 }
ProtoBufChildKey{ parentId=1, branchNumber=1 } : SegmentInfo{ calculatedSegment=33, storedSegment=173 }
ProtoBufChildKey{ parentId=1, branchNumber=2 } : SegmentInfo{ calculatedSegment=33, storedSegment=191 }
ProtoBufChildKey{ parentId=1, branchNumber=7 } : SegmentInfo{ calculatedSegment=33, storedSegment=241 }
ProtoBufChildKey{ parentId=2, branchNumber=4 } : SegmentInfo{ calculatedSegment=155, storedSegment=147 }

Backup segments
10, 11, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 36, 39, 48, 49, 50, 51, 53, 54, 55, 56, 57, 67, 68, 69, 70, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 168, 178, 179, 180, 181, 216, 217, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 252, 253

ProtoBufChildKey{ parentId=0, branchNumber=0 } : SegmentInfo{ calculatedSegment=116, storedSegment=168 }
ProtoBufChildKey{ parentId=0, branchNumber=7 } : SegmentInfo{ calculatedSegment=116, storedSegment=139 }
ProtoBufChildKey{ parentId=1, branchNumber=0 } : SegmentInfo{ calculatedSegment=33, storedSegment=226 }
ProtoBufChildKey{ parentId=1, branchNumber=4 } : SegmentInfo{ calculatedSegment=33, storedSegment=53 }
ProtoBufChildKey{ parentId=1, branchNumber=6 } : SegmentInfo{ calculatedSegment=33, storedSegment=29 }
ProtoBufChildKey{ parentId=1, branchNumber=8 } : SegmentInfo{ calculatedSegment=33, storedSegment=11 }
ProtoBufChildKey{ parentId=2, branchNumber=0 } : SegmentInfo{ calculatedSegment=155, storedSegment=116 }
ProtoBufChildKey{ parentId=2, branchNumber=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=11 }
ProtoBufChildKey{ parentId=2, branchNumber=6 } : SegmentInfo{ calculatedSegment=155, storedSegment=25 }
ProtoBufChildKey{ parentId=2, branchNumber=7 } : SegmentInfo{ calculatedSegment=155, storedSegment=136 }
```


# Node 3

```
*************
Primary segments
0, 1, 2, 6, 7, 8, 9, 10, 11, 33, 34, 35, 37, 38, 41, 42, 43, 44, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 164, 167, 168, 178, 179, 180, 181, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 252, 253, 254, 255

JavaParentKey{ parentId=1 } : SegmentInfo{ calculatedSegment=137, storedSegment=99 }
JavaParentKey{ parentId=2 } : SegmentInfo{ calculatedSegment=206, storedSegment=121 }

Backup segments
3, 4, 5, 58, 59, 61, 62, 63, 64, 65, 66, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 147, 148, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 165, 166, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 184, 187, 188, 189, 190, 191, 192, 193, 194, 195, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 218, 245, 246, 247, 248, 249, 250, 251

JavaParentKey{ parentId=0 } : SegmentInfo{ calculatedSegment=35, storedSegment=72 }

*************
Primary segments
0, 1, 2, 6, 7, 8, 9, 10, 11, 33, 34, 35, 37, 38, 41, 42, 43, 44, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 164, 167, 168, 178, 179, 180, 181, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 252, 253, 254, 255

JavaChildKey{ parentId=0, branchNumber=1 } : SegmentInfo{ calculatedSegment=202, storedSegment=78 }
JavaChildKey{ parentId=0, branchNumber=4 } : SegmentInfo{ calculatedSegment=159, storedSegment=253 }
JavaChildKey{ parentId=0, branchNumber=6 } : SegmentInfo{ calculatedSegment=174, storedSegment=60 }
JavaChildKey{ parentId=1, branchNumber=1 } : SegmentInfo{ calculatedSegment=19, storedSegment=124 }
JavaChildKey{ parentId=1, branchNumber=2 } : SegmentInfo{ calculatedSegment=214, storedSegment=122 }
JavaChildKey{ parentId=1, branchNumber=4 } : SegmentInfo{ calculatedSegment=218, storedSegment=85 }
JavaChildKey{ parentId=2, branchNumber=1 } : SegmentInfo{ calculatedSegment=203, storedSegment=123 }
JavaChildKey{ parentId=2, branchNumber=4 } : SegmentInfo{ calculatedSegment=181, storedSegment=219 }

Backup segments
3, 4, 5, 58, 59, 61, 62, 63, 64, 65, 66, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 147, 148, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 165, 166, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 184, 187, 188, 189, 190, 191, 192, 193, 194, 195, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 218, 245, 246, 247, 248, 249, 250, 251

JavaChildKey{ parentId=0, branchNumber=0 } : SegmentInfo{ calculatedSegment=46, storedSegment=105 }
JavaChildKey{ parentId=0, branchNumber=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=207 }
JavaChildKey{ parentId=0, branchNumber=5 } : SegmentInfo{ calculatedSegment=203, storedSegment=104 }
JavaChildKey{ parentId=0, branchNumber=8 } : SegmentInfo{ calculatedSegment=35, storedSegment=161 }
JavaChildKey{ parentId=1, branchNumber=0 } : SegmentInfo{ calculatedSegment=26, storedSegment=3 }
JavaChildKey{ parentId=1, branchNumber=7 } : SegmentInfo{ calculatedSegment=114, storedSegment=209 }
JavaChildKey{ parentId=2, branchNumber=0 } : SegmentInfo{ calculatedSegment=181, storedSegment=171 }
JavaChildKey{ parentId=2, branchNumber=2 } : SegmentInfo{ calculatedSegment=138, storedSegment=66 }
JavaChildKey{ parentId=2, branchNumber=5 } : SegmentInfo{ calculatedSegment=231, storedSegment=157 }
JavaChildKey{ parentId=2, branchNumber=6 } : SegmentInfo{ calculatedSegment=90, storedSegment=187 }

*************
Primary segments
0, 1, 2, 6, 7, 8, 9, 10, 11, 33, 34, 35, 37, 38, 41, 42, 43, 44, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 164, 167, 168, 178, 179, 180, 181, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 252, 253, 254, 255

ProtoBufParentKey{ parentId=1 } : SegmentInfo{ calculatedSegment=33, storedSegment=52 }

Backup segments
3, 4, 5, 58, 59, 61, 62, 63, 64, 65, 66, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 147, 148, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 165, 166, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 184, 187, 188, 189, 190, 191, 192, 193, 194, 195, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 218, 245, 246, 247, 248, 249, 250, 251

ProtoBufParentKey{ parentId=0 } : SegmentInfo{ calculatedSegment=116, storedSegment=187 }
ProtoBufParentKey{ parentId=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=5 }

*************
Primary segments
0, 1, 2, 6, 7, 8, 9, 10, 11, 33, 34, 35, 37, 38, 41, 42, 43, 44, 52, 60, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 94, 95, 96, 97, 98, 99, 100, 101, 102, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 136, 137, 138, 139, 140, 141, 163, 164, 167, 168, 178, 179, 180, 181, 196, 197, 198, 199, 200, 201, 219, 220, 221, 222, 223, 252, 253, 254, 255

ProtoBufChildKey{ parentId=0, branchNumber=0 } : SegmentInfo{ calculatedSegment=116, storedSegment=168 }
ProtoBufChildKey{ parentId=0, branchNumber=2 } : SegmentInfo{ calculatedSegment=116, storedSegment=223 }
ProtoBufChildKey{ parentId=0, branchNumber=7 } : SegmentInfo{ calculatedSegment=116, storedSegment=139 }
ProtoBufChildKey{ parentId=1, branchNumber=8 } : SegmentInfo{ calculatedSegment=33, storedSegment=11 }
ProtoBufChildKey{ parentId=2, branchNumber=0 } : SegmentInfo{ calculatedSegment=155, storedSegment=116 }
ProtoBufChildKey{ parentId=2, branchNumber=1 } : SegmentInfo{ calculatedSegment=155, storedSegment=96 }
ProtoBufChildKey{ parentId=2, branchNumber=2 } : SegmentInfo{ calculatedSegment=155, storedSegment=11 }
ProtoBufChildKey{ parentId=2, branchNumber=3 } : SegmentInfo{ calculatedSegment=155, storedSegment=9 }
ProtoBufChildKey{ parentId=2, branchNumber=5 } : SegmentInfo{ calculatedSegment=155, storedSegment=94 }
ProtoBufChildKey{ parentId=2, branchNumber=7 } : SegmentInfo{ calculatedSegment=155, storedSegment=136 }

Backup segments
3, 4, 5, 58, 59, 61, 62, 63, 64, 65, 66, 71, 72, 73, 74, 75, 76, 90, 91, 92, 93, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 147, 148, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 165, 166, 169, 170, 171, 172, 173, 174, 175, 176, 177, 182, 184, 187, 188, 189, 190, 191, 192, 193, 194, 195, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 218, 245, 246, 247, 248, 249, 250, 251

ProtoBufChildKey{ parentId=0, branchNumber=3 } : SegmentInfo{ calculatedSegment=116, storedSegment=64 }
ProtoBufChildKey{ parentId=0, branchNumber=4 } : SegmentInfo{ calculatedSegment=116, storedSegment=71 }
ProtoBufChildKey{ parentId=0, branchNumber=5 } : SegmentInfo{ calculatedSegment=116, storedSegment=187 }
ProtoBufChildKey{ parentId=0, branchNumber=6 } : SegmentInfo{ calculatedSegment=116, storedSegment=62 }
ProtoBufChildKey{ parentId=0, branchNumber=8 } : SegmentInfo{ calculatedSegment=116, storedSegment=74 }
ProtoBufChildKey{ parentId=1, branchNumber=1 } : SegmentInfo{ calculatedSegment=33, storedSegment=173 }
ProtoBufChildKey{ parentId=1, branchNumber=2 } : SegmentInfo{ calculatedSegment=33, storedSegment=191 }
ProtoBufChildKey{ parentId=1, branchNumber=3 } : SegmentInfo{ calculatedSegment=33, storedSegment=64 }
ProtoBufChildKey{ parentId=1, branchNumber=5 } : SegmentInfo{ calculatedSegment=33, storedSegment=218 }
ProtoBufChildKey{ parentId=2, branchNumber=4 } : SegmentInfo{ calculatedSegment=155, storedSegment=147 }
ProtoBufChildKey{ parentId=2, branchNumber=8 } : SegmentInfo{ calculatedSegment=155, storedSegment=3 }
```

