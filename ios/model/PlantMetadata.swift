import Foundation

struct PlantMetadata: Serializable {
    let uuid: String
    let title: String
    let subtitle: String
    let timestamp: Date
    let image: String
    static func toObject(map: [String: Any]) -> PlantMetadata? {
        return map["uuid"] == nil || map["title"] == nil || map["subtitle"] == nil || map["timestamp"] == nil || map["image"] == nil ? nil : PlantMetadata(uuid: map["uuid"]! as! String, title: map["title"]! as! String, subtitle: map["subtitle"]! as! String, timestamp: DateService.toDate(from: map["timestamp"]! as! String), image: map["image"]! as! String)
    }
}

