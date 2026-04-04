package seq.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import seq.enitities.Comment
import seq.enitities.CommentBoard
import seq.services.CommentService
import java.util.UUID

@RestController
@RequestMapping("v1/comment")
class CommentController(
    private val commentService: CommentService
) {
    @PostMapping("board/{name}")
    fun newCommentBoard(@PathVariable("name") name: String) {
        commentService.newCommentBoard(name)
    }

    @GetMapping("board/{id}")
    fun getCommentBoard(@PathVariable("id") id: UUID) : ResponseEntity<CommentBoard> {
        return ResponseEntity.status(HttpStatus.FOUND).body(commentService.getCommentBoardByID(id))
    }

    @GetMapping("/{id}")
    fun getComments(@PathVariable("id") id: UUID) : ResponseEntity<List<Comment>> {
        return ResponseEntity.status(HttpStatus.FOUND).body(commentService.getCommentsByID(id))
    }

    @PostMapping()
    fun postComment(@RequestBody comment: Comment) {
        commentService.addComment(comment)
    }

    @PatchMapping("/vote/{comment_id}/{isUpvote}")
    fun voteOnComment(@PathVariable("isUpvote") isUpvote: Boolean, @PathVariable("comment_id") comment_id: UUID) {
        commentService.voteOnComment(comment_id, isUpvote)
    }
}