package com.sivalabs.bookmarker.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.sivalabs.bookmarker.entity.Bookmark
import java.time.LocalDateTime

data class BookmarkDTO(
    var id: Long = 0,
    var url: String = "",
    var description: String = "",
    @JsonProperty("created_by")
    var createdBy: Long = 0,
    @JsonProperty("created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @JsonProperty("updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {
    companion object {
        fun fromEntity(bm: Bookmark) = BookmarkDTO(
                bm.id,
                bm.url,
                bm.description,
                bm.createdBy.id,
                bm.createdAt,
                bm.updatedAt
        )
    }
}