package br.com.sekai.book_exchange.service

import br.com.sekai.book_exchange.data.vo.v1.PostsVO
import br.com.sekai.book_exchange.exceptions.ResourceNotFoundException
import br.com.sekai.book_exchange.mapper.DozerMapper

import br.com.sekai.book_exchange.mapper.toArrayVO
import br.com.sekai.book_exchange.model.Posts
import br.com.sekai.book_exchange.repository.PostsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PostService {
    @Autowired
    private lateinit var postsRepository: PostsRepository

    private val logger = Logger.getLogger(PostService::class.java.name)

    fun findAll(): List<PostsVO>{
        logger.info("FINDING ALL POSTS!!")
        val posts = postsRepository.findAll()
        return posts.toArrayVO()
//        return DozerMapper.parserListObject(postsRepository.findAll(), PostsVO::class.java)
    }

    fun findById(id: Long): PostsVO {
        logger.info("FINDING POST BY ID!!")
        val post = postsRepository.findById(id).orElseThrow { ResourceNotFoundException("Id Not Found") }
        return DozerMapper.parserObject(post, PostsVO::class.java)
    }

    fun createPosts(PostsVO: PostsVO): PostsVO{
        logger.info("INSERT NEW POST!!")
        val post: Posts = DozerMapper.parserObject(PostsVO, Posts::class.java)

        return DozerMapper.parserObject(postsRepository.save(post), PostsVO::class.java)
    }

//    fun update(PostsVO: PostsVO): PostsVO{
//        logger.info("UPDATE A BOOK WITH ID ${PostsVO.bookID}${PostsVO.userID}!!")
//        val post : Posts = postsRepository.findById(PostsVO.key).orElseThrow {
//            ResourceNotFoundException("POST WITH IS ID IS NOT FOUND!!")
//        }
////        post.author = PostsVO.author
////        post.imgURL= PostsVO.imgURL
////        post.resume = PostsVO.resume
////        post.publisher = PostsVO.publisher
////        post.title= PostsVO.title
//
//        return DozerMapper.parserObject(postsRepository.save(post), PostsVO::class.java)
//    }
//    fun update(PostsVO: PostsVO): PostsVO{
//        logger.info("UPDATE A BOOK WITH ID ${PostsVO.bookID}${PostsVO.userID}!!")
//        val post : Posts = postsRepository.findById(PostsVO.key).orElseThrow {
//            ResourceNotFoundException("POST WITH IS ID IS NOT FOUND!!")
//        }
////        post.author = PostsVO.author
////        post.imgURL= PostsVO.imgURL
////        post.resume = PostsVO.resume
////        post.publisher = PostsVO.publisher
////        post.title= PostsVO.title
//
//        return DozerMapper.parserObject(postsRepository.save(post), PostsVO::class.java)
//    }

    fun delete(id: Long){
        logger.info("INSERT NEW POST!!")
        val post: Posts = postsRepository.findById(id).orElseThrow{
            ResourceNotFoundException("POST WITH IS ID IS NOT FOUND!!")

        }
        postsRepository.delete(post)
    }
}
