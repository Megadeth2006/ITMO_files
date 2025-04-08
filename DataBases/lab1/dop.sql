SELECT Human.*
FROM Human
JOIN Human_Events ON Human.human_id = Human_Events.human_id
JOIN Event ON Human_Events.event_id = Event.event_id
WHERE Human.name = 'Василий' AND Event.description = 'Нападение в джунглях';