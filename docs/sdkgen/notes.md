# NotesManager


- [Convert content to Box Note](#convert-content-to-box-note)

## Convert content to Box Note

Creates a Box Note (`.boxnote` file) from supported source content. See the `content_format` field for supported formats.

This operation is performed by calling function `createNoteConvertV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-notes-convert/).

<!-- sample post_notes_convert_v2026.0 -->
```
client.getNotes().createNoteConvertV2026R0(new NotesConvertRequestBodyV2026R0.Builder(markdownContent, new FolderReferenceV2026R0("0"), noteName).contentFormat(NotesConvertRequestBodyV2026R0ContentFormatField.MARKDOWN).build())
```

### Arguments

- requestBody `NotesConvertRequestBodyV2026R0`
  - Request body of createNoteConvertV2026R0 method
- headers `CreateNoteConvertV2026R0Headers`
  - Headers of createNoteConvertV2026R0 method


### Returns

This function returns a value of type `NotesConvertResponseV2026R0`.

The note was created successfully.


