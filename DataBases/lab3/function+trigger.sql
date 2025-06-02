CREATE OR REPLACE FUNCTION set_human_reactions_scared()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.role = 'нападающий' THEN
        -- Удалим старые реакции для людей в этом событии
        DELETE FROM Human_Reactions
        WHERE event_id = NEW.event_id
          AND human_id IN (
              SELECT human_id
              FROM Human_Events
              WHERE event_id = NEW.event_id
          );

        -- Вставим новые реакции "испуг"
        INSERT INTO Human_Reactions(human_id, event_id, reaction)
        SELECT human_id, NEW.event_id, 'испуг'
        FROM Human_Events
        WHERE event_id = NEW.event_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_set_scared_reaction
AFTER INSERT ON Dinosaur_Events
FOR EACH ROW
EXECUTE FUNCTION set_human_reactions_scared();