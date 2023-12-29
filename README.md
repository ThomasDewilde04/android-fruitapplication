
# Fruit Application

The Fruit App is a simple application that displays a list of fruits and their details. The Fruit App integrates the Fruityvice public API to fetch information about different fruits available in the API's database. The app leverages the API to provide users with a diverse range of fruits and their details. The app has a navigation bar that allows user to quickly switch between different pages. Seeing as this application only has 2 major pages (Fruits and an about page), navigation can be done quickly. 


## API Reference

#### Get all items

```http
  GET /api/fruit/all
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. all |

#### Get item

```http
  GET /api/fruit/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of item to fetch |


## Authors

- [@ThomasDewilde04](https://github.com/ThomasDewilde04)


## Badges

![API](https://img.shields.io/badge/API-FF6347?style=for-the-badge)

