package br.com.sekai.book_exchange.controller


import br.com.sekai.book_exchange.data.vo.v1.PostsVO
import br.com.sekai.book_exchange.service.PostService
import br.com.sekai.book_exchange.uitl.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/posts/v1")
class PostsController {
    @Autowired
    private lateinit var postsService: PostService

    @GetMapping(produces = [MediaType.APPLICATION_JSON])
    fun findAll(): List<PostsVO>{

        return postsService.findAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun create(@RequestBody postsVO: PostsVO): PostsVO{
         return  postsService.createPosts(postsVO)
    }
}
