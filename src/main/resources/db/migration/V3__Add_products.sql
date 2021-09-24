alter table tea drop column year;
alter table tea drop column perfume_gender;
alter table tea drop column fragrance_middle_notes;

delete from tea;

insert into tea
(id, country, description, filename, fragrance_base_notes,
 perfume_title, perfumer, price, type, volume)
values (1, 'Россия', 'В яркой палитре Greenfield Golden Ceylon тонкие оттенки пряного вкуса сочетаются с силой и полнотой, а изысканный аромат завершает неповторимый букет и подчеркивает благородное происхождение напитка. ',
        'D:\botanstvo\java\chaaaaaaai\src\main\resources\uploads\ggc.png',
        'Пряный', 'Golden Ceylon',
        'Greenfield', 5, 'Чёрный', '100');
