import Foundation

struct NewsMetadata: Serializable {
    let uuid: String
    let title: String
    let timestamp: Date
    let image: String
    static func toObject(map: [String: Any]) -> NewsMetadata? {
        return map["uuid"] == nil || map["title"] == nil || map["timestamp"] == nil || map["image"] == nil ? nil : NewsMetadata(uuid: map["uuid"]! as! String, title: map["title"]! as! String, timestamp: DateService.toDate(from: map["timestamp"]! as! String), image: map["image"]! as! String)
    }
}
