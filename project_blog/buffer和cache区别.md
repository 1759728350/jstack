buffer是缓冲的意思
cache缓存

buffer的主要目的是减少磁盘io次数,每一次都写到一个定额的数据后再写入到磁盘
或者是一次性从磁盘读定额大小的数据到内存
主要目的是减少磁盘与内存的读写次数

比如io流里的# BufferedInputStream
fileInputStream一次读的一个字节,BufferedInputStream里有一个buffer数组一次读取八千多个字节

[深入探讨可见此处](https://blog.csdn.net/qq_40677181/article/details/122985152?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-4-122985152-blog-95891286.t0_layer_eslanding_s&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-4-122985152-blog-95891286.t0_layer_eslanding_s&utm_relevant_index=9)
[2](https://blog.csdn.net/weixin_30706507/article/details/95891286?ops_request_misc=&request_id=&biz_id=102&utm_term=%E4%B8%BA%E4%BB%80%E4%B9%88%E7%BC%93%E5%86%B2%E5%8C%BA%E8%83%BD&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-1-95891286.142^v42^new_blog_pos_by_title,185^v2^control&spm=1018.2226.3001.4187)
[3](https://blog.csdn.net/fuhanghang/article/details/109756207?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166119357516781432926632%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166119357516781432926632&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~baidu_landing_v2~default-5-109756207-null-null.142^v42^new_blog_pos_by_title,185^v2^control&utm_term=Buffer&spm=1018.2226.3001.4187)

cache的主要目的是减少读硬盘数据的机会
比如将一些计算完的结果留在cache里,以后再次需要该结果就不用去读程序再运算出来了