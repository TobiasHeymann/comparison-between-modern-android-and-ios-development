class DatabaseService {
    static func getLastTenNews(callback: @escaping ([News]?) -> ()) {
        TPFirebaseFirestore.addCollectionSnapshotListener(queryBuilder: TPFirebaseFirestoreQueryBuilder(collectionName: News.COLLECTION_NAME).orderBy(field: "metadata.timestamp", descending: true).limit(limit: 10)) {
            result, error in
            if let resultObject = result {
                var list = [News]()
                resultObject.forEach {
                    tpFirebaseFirestoreDocument in
                    if let news = News.toObject(map: tpFirebaseFirestoreDocument.data) {
                        list.append(news)
                    }
                }

                callback(list)
            }

            if error != nil {
                callback(nil)
            }
        }
    }

    static func getLastFivePlants(callback: @escaping ([Plant]?) -> ()) {
        TPFirebaseFirestore.addCollectionSnapshotListener(queryBuilder: TPFirebaseFirestoreQueryBuilder(collectionName: Plant.COLLECTION_NAME).orderBy(field: "metadata.timestamp", descending: true).limit(limit: 5)) {
            result, error in
            if let resultObject = result {
                var list = [Plant]()
                resultObject.forEach {
                    tpFirebaseFirestoreDocument in
                    if let plant = Plant.toObject(map: tpFirebaseFirestoreDocument.data) {
                        list.append(plant)
                    }
                }

                callback(list)
            }

            if error != nil {
                callback(nil)
            }
        }
    }

    static func getAllPlants(callback: @escaping ([Plant]) -> ()) {
        TPFirebaseFirestore.getDocuments(collectionName: Plant.COLLECTION_NAME) {
            result, error in
            if let resultObject = result {
                var list = [Plant]()
                resultObject.forEach {
                    tpFirebaseFirestoreDocument in
                    if let plant = Plant.toObject(map: tpFirebaseFirestoreDocument.data) {
                        list.append(plant)
                    }
                }

                callback(list)
            }

            if error != nil {
                callback([])
            }
        }
    }

    static func searchPlantById(uuid: String, callback: @escaping (Plant) -> ()) {
        TPFirebaseFirestore.getDocument(collectionName: "plants", documentId: uuid) {
            result, error in
            if let resultObject = result {
                if let it = Plant.toObject(map: resultObject.data) {
                    callback(it)
                }
            }
        }
    }
}
