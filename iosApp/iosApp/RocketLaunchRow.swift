//
//  RocketLaunchRow.swift
//  iosApp
//
//  Created by Bilal-PC on 24.09.2026.
//

import SharedLogic
import SwiftUI

struct RocketLaunchRow: View {
    var rocketLaunch: RocketLaunch

    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 10.0) {
                Text("\(rocketLaunch.missionName)")
                    .font(.system(size: 18))
                    .bold()
                    .fixedSize(horizontal: false, vertical: true)
                Text(launchText).foregroundColor(launchColor)
                Text("Launch year: \(String(rocketLaunch.launchYear))")
                Text("\(rocketLaunch.status.description_)")
            }
            Spacer()
        }
    }
}

extension RocketLaunchRow {
    private var launchText: String {
        let isSuccess = rocketLaunch.status.id == 3
        return isSuccess ? "Successful" : "Unsuccessful"
    }

    private var launchColor: Color {
        let isSuccess = rocketLaunch.status.id == 3
        return isSuccess ? Color.green : Color.red
    }
}
