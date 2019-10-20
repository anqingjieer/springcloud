### arrayList和linkList的区别
## 1.ArrayList的底层是object数组实现的，支持使用下标访问；但是linkList底层采用的是双向链表数据结构实现的。不支持下标访问**
**插入和追加是否有影响**

**https://zhuanlan.zhihu.com/p/21673805**
`JDK1.8之前
 JDK1.8 之前 HashMap 底层是 数组和链表 结合在一起使用也就是 链表散列。HashMap 通过 key 的 hashCode 经过扰动函数处理过后
 得到 hash 值，然后通过 (n - 1) & hash 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，
 就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。
 所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 
 换句话说使用扰动函数之后可以减少碰撞。
 JDK 1.8 HashMap 的 hash 方法源码:`