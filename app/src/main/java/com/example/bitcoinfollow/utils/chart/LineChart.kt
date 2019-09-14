package com.example.bitcoinfollow.utils.chart

class LineChart(
    val model: String?,
    val year: Int
) {


    private constructor(builder: Builder) : this(builder.model, builder.year)

    class Builder {
        var model: String? = null
            private set

        var year: Int = 0
            private set

        fun model(model: String) = apply { this.model = model }

        fun year(year: Int) = apply { this.year = year }

        fun build() = LineChart(this)
    }


}