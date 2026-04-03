package seq.services

import org.springframework.stereotype.Service
import seq.Constants
import seq.enitities.Comment
import seq.enitities.CommentBoard
import seq.repositories.CommentBoardRepository
import java.util.UUID

var mockCommentBoardList = mutableListOf<CommentBoard>()

@Service
class CommentService(
    private val commentBoardRepository: CommentBoardRepository
) {
    fun newCommentBoard(boardName: String)
    {
        if (boardName.isNullOrBlank()) throw IllegalArgumentException("Board name cannot be empty")
        val board = CommentBoard(name = boardName)
        commentBoardRepository.save(board)
    }

    fun addComment(comment: Comment, commentBoardId: UUID)
    {
        if (comment.body.length > 512) throw Exception(Constants.COMMENT_TOO_LONG)
        if (comment.body.isNullOrBlank()) throw Exception(Constants.COMMENT_IS_EMPTY)

        val commentBoard = getCommentBoardByID(commentBoardId)

        commentBoard.children.add(comment)
        commentBoardRepository.save(commentBoard)
    }

    fun getCommentBoardByID(commentBoardId: UUID): CommentBoard {
        return commentBoardRepository.findById(commentBoardId)
            .orElseThrow { Exception("CommentBoard not found") }
    }

    fun voteOnComment(commentBoardId: UUID, commentId: UUID, isUpvote: Boolean) {
        val commentBoard = getCommentBoardByID(commentBoardId)
        if (isUpvote) commentBoard.children.find { it.id == commentId }?.let { it.votes.upvotes++ }
        else commentBoard.children.find { it.id == commentId }?.let { it.votes.downvotes++ }

        commentBoardRepository.save(commentBoard)
    }
}

