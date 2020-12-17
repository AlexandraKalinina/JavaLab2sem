/*
use education*/

db.users.insertMany([
    {
        name: 'Sanya',
        email: 'alekskalin1na@yandex.ru',
        password: 'hashPassowrd',
        years: '20',
        city: 'Kazan'
    },
    {
        name: 'NeSasha',
        email: 'emailOfNeSasha@gmail.com',
        password: 'hashPassowrd',
        messages: ['супер!советую прочитать', 'концовка как-то не очень вышла...']
    },
    {
        name: 'ItsAlsoNeSasha',
        email: 'itsme@gmail.com',
        password: 'hashPassowrd',
        city: 'CityPodPodoshvoi'
    }
]);
db.users.drop();

db.users.find({_id : ObjectId('5fdb2fa2b110e72b72e725f2')})

db.users.update({_id : ObjectId('5fdb2fa2b110e72b72e725f1')},
    {
        $set:{
            years: '22'
        }
    },
)
db.users.update({_id :  ObjectId('5fdb2fa2b110e72b72e725f0')}, {$push : {messages: 'cool'}})

db.books.insertMany([
    {
        name: 'Java',
        path: 'c:/books/txt/Java',
        size: 3
    },
    {
        name: 'Asya',
        path: 'c:/books/txt/Asya',
    }
]);

db.authors.insertMany([
    {
        name: 'Ivan',
        surname: 'Turgenev',
        patronymic: 'Sergeevich'
    },
    {
        name: 'Herbert',
        surname: 'Schildt'
    }
]);

db.genres.insertMany([
    {
        name: 'Novel',
    },
    {
        name: 'Tutorial'
    }
]);

db.books.update({_id : ObjectId('5fdb40d5b110e72b72e725fa')}, {
    $set: {
        authors: {
            author1: ObjectId('5fdb41c8b110e72b72e725fe')
        },
        genres: {
            genres1: ObjectId('5fdb424bb110e72b72e72601')
        }
    }
});

db.books.update({_id : ObjectId('5fdb40d5b110e72b72e725fb')}, {
    $set: {
        authors: {
            author1: ObjectId('5fdb41c8b110e72b72e725fd')
        },
        genres: {
            genres1: ObjectId('5fdb424bb110e72b72e72600')
        }
    }
});
