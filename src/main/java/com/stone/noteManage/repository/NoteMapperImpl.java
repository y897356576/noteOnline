package com.stone.noteManage.repository;

import com.stone.core.model.Note;
import com.stone.core.model.NoteGenre;
import com.stone.core.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 石头 on 2018/2/22.
 */
@Component
public class NoteMapperImpl {

    @Autowired
    private NoteMapper noteMapper;

    public Boolean createNote(Note note) {
        if (note == null) {
            return false;
        }
        noteMapper.createNote(note);

        if (StringUtils.isNotBlank(note.getNoteFileId()) && note.getNoteFile() != null) {
            noteMapper.createNoteFile(note.getNoteFile());
        }

        if (CollectionUtils.isNotEmpty(note.getContents())) {
            noteMapper.createNoteContents(note.getContents());
        }

        return true;
    }

    public NoteGenre getGenreByName(String userId, String genreName) {
        if (StringUtils.isBlank(genreName)) {
            return null;
        }
        return noteMapper.getGenreByName(userId, genreName);
    }

    public Boolean createNoteGenre(NoteGenre genre) {
        if (genre == null) {
            return false;
        }
        Integer result = noteMapper.createNoteGenre(genre);
        return result == 1;
    }

}
