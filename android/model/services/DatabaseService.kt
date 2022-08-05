package com.tobiasheymann.ep.model.services

import com.tobiasheymann.ep.model.News
import com.tobiasheymann.ep.model.Plant
import de.thm.tp.library.firebase.firestore.TPFirebaseFirestore
import de.thm.tp.library.firebase.firestore.TPFirebaseFirestoreQueryBuilder

class DatabaseService {
    companion object {
        fun getLastTenNews(callback: (List<News>?) -> Unit) {
            TPFirebaseFirestore.addCollectionSnapshotListener(
                TPFirebaseFirestoreQueryBuilder(News.COLLECTION_NAME)
                    .orderBy("metadata.timestamp", true)
                    .limit(10)
            ) { result, error ->
                result?.let { resultObject ->
                    val list = mutableListOf<News>()
                    resultObject.forEach { tpFirebaseFirestoreDocument ->
                        News.toObject(tpFirebaseFirestoreDocument.data)?.let { news ->
                            list.add(news)
                        }
                    }
                    callback(list)
                }
                if (error != null) {
                    callback(null)
                }
            }
        }

        fun getLastFivePlants(callback: (List<Plant>?) -> Unit) {
            TPFirebaseFirestore.addCollectionSnapshotListener(
                TPFirebaseFirestoreQueryBuilder(Plant.COLLECTION_NAME)
                    .orderBy("metadata.timestamp", true)
                    .limit(5)
            ) { result, error ->
                result?.let { resultObject ->
                    val list = mutableListOf<Plant>()
                    resultObject.forEach { tpFirebaseFirestoreDocument ->
                        Plant.toObject(tpFirebaseFirestoreDocument.data)?.let { plant ->
                            list.add(plant)
                        }
                    }
                    callback(list)
                }
                if (error != null) {
                    callback(null)
                }
            }
        }

        fun getAllPlants(callback: (List<Plant>) -> Unit) {
            TPFirebaseFirestore.getDocuments(Plant.COLLECTION_NAME) { result, error ->
                result?.let { resultObject ->
                    val list = mutableListOf<Plant>()
                    resultObject.forEach { tpFirebaseFirestoreDocument ->
                        Plant.toObject(tpFirebaseFirestoreDocument.data)?.let { plant ->
                            list.add(plant)
                        }
                    }
                    callback(list)
                }
                if (error != null) {
                    callback(listOf())
                }
            }
        }

        fun searchPlantById(uuid: String, callback: (Plant) -> Unit) {
            TPFirebaseFirestore.getDocument("plants", uuid) { result, error ->
                result?.let { resultObject ->
                    Plant.toObject(resultObject.data)?.let { callback(it) }
                }
            }
        }
    }
}