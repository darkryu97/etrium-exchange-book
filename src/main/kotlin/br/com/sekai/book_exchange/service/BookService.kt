package br.com.sekai.book_exchange.service

import br.com.sekai.book_exchange.data.vo.v1.BookVO
import br.com.sekai.book_exchange.exceptions.ResourceNotFoundException
import br.com.sekai.book_exchange.mapper.DozerMapper
import br.com.sekai.book_exchange.mapper.bookListToListBookVO
import br.com.sekai.book_exchange.model.Book
import br.com.sekai.book_exchange.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {
    @Autowired
    private lateinit var bookRepository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll(): List<BookVO>{
        logger.info("FINDING ALL BOOKS!!")
        return bookListToListBookVO(bookRepository.findAll() as ArrayList<Book>)
//        return DozerMapper.parserListObject(bookRepository.findAll(), BookVO::class.java)
    }

    fun findById(id: Long): BookVO {
        logger.info("FINDING BOOK BY ID!!")
        val book = bookRepository.findById(id).orElseThrow { ResourceNotFoundException("Id Not Found") }
        return DozerMapper.parserObject(book, BookVO::class.java)
    }

    fun createBook(bookVO: BookVO): BookVO{
        logger.info("INSERT NEW BOOK!!")
        val book: Book = DozerMapper.parserObject(bookVO, Book::class.java)

        return DozerMapper.parserObject(bookRepository.save(book), BookVO::class.java)
    }

    fun update(bookVO: BookVO): BookVO{
        logger.info("UPDATE A BOOK WITH ID ${bookVO.key}!!")
        val book : Book = bookRepository.findById(bookVO.key).orElseThrow {
            ResourceNotFoundException("BOOK WITH IS ID IS NOT FOUND!!")
        }
        book.author = bookVO.author
        book.imgURL= bookVO.imgURL
        book.resume = bookVO.resume
        book.publisher = bookVO.publisher
        book.title= bookVO.title

        return DozerMapper.parserObject(bookRepository.save(book), BookVO::class.java)
    }

    fun delete(id: Long){
        logger.info("INSERT NEW BOOK!!")
        val book: Book = bookRepository.findById(id).orElseThrow{
            ResourceNotFoundException("BOOK WITH IS ID IS NOT FOUND!!")

        }
        bookRepository.delete(book)
    }
}
