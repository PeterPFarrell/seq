package seq.services

import org.springframework.stereotype.Service
import seq.Constants
import seq.enitities.Comment
import seq.enitities.CommentBoard
import seq.repositories.CommentBoardRepository
import seq.repositories.CommentRepository
import java.util.UUID

var mockCommentBoardList = mutableListOf<CommentBoard>()

@Service
class CommentService(
    private val commentBoardRepository: CommentBoardRepository,
    private val commentRepository: CommentRepository,
) {
    fun newCommentBoard(boardName: String)
    {
        if (boardName.isNullOrBlank()) throw IllegalArgumentException("Board name cannot be empty")
        val board = CommentBoard(name = boardName)
        commentBoardRepository.save(board)
    }

    fun addComment(comment: Comment)
    {
        if (comment.body.length > 512) throw Exception(Constants.COMMENT_TOO_LONG)
        if (comment.body.isNullOrBlank()) throw Exception(Constants.COMMENT_IS_EMPTY)
        commentRepository.save(comment)
    }

    fun getCommentBoardByID(commentBoardId: UUID): CommentBoard {
        return commentBoardRepository.findById(commentBoardId)
            .orElseThrow { Exception("CommentBoard not found") }
    }

    fun getCommentsByID(commentBoardId: UUID): List<Comment> {
        return commentRepository.findAllByParentId(commentBoardId)
    }

    fun voteOnComment(commentId: UUID, isUpvote: Boolean) {
        val comment : Comment = commentRepository.findById(commentId)
            .orElseThrow { Exception(Constants.COMMENT_DOES_NOT_EXIST) }

        if (isUpvote) comment.votes.upvotes++
        else comment.votes.downvotes++

        commentRepository.save(comment)
    }
}

