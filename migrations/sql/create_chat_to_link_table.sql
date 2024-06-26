create table if not exists chat_to_link
(
    chat_id bigint ,
    link_id bigint,
    FOREIGN KEY (chat_id) REFERENCES chat (chat_id) ON DELETE CASCADE,
    FOREIGN KEY (link_id) REFERENCES link (link_id) ON DELETE CASCADE
);
