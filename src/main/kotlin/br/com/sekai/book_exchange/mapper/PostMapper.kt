package br.com.sekai.book_exchange.mapper


import br.com.sekai.book_exchange.data.vo.v1.PostsVO
import br.com.sekai.book_exchange.model.Book

import br.com.sekai.book_exchange.model.Posts

//fun PostsVO.toEntity() = Posts(this.key, this.postCount, this.date, this.userID, this.bookID, this.bookInterests)
//fun Posts.toVO() = PostsVO(this.id, this.postCount, this.date, this.userID, this.bookID, this.bookInterests)
fun PostsVO.toEntity() = Posts(this.key, this.postCount, this.date, this.userID.toEntity(), this.bookID.toEntity(), this.bookInterests)
fun Posts.toVO() = PostsVO(this.id, this.postCount, this.date, this.userID.toVOnoPassword(), this.bookID.toVO(), this.bookInterests)

fun listPostsVOToListPosts(postsVO: ArrayList<PostsVO>): ArrayList<Posts> {
        val toPosts = ArrayList<Posts>()
        postsVO.forEach { postVO ->
           toPosts.add( postVO.toEntity())
        }

    return toPosts
}

fun MutableList<Posts>.toArrayVO( ) : List<PostsVO> = this.map { it.toVO() }
fun MutableList<PostsVO>.toArrayEntity(): List<Posts> = this.map{it.toEntity()}
