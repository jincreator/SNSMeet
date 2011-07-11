/*
 * Copyright 2009 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.snsmeet.zxing.multi;


import java.util.Hashtable;

import org.snsmeet.zxing.BinaryBitmap;
import org.snsmeet.zxing.NotFoundException;
import org.snsmeet.zxing.Result;

/**
 * Implementation of this interface attempt to read several barcodes from one image.
 *
 * @see org.snsmeet.zxing.Reader
 * @author Sean Owen
 */
public interface MultipleBarcodeReader {

  Result[] decodeMultiple(BinaryBitmap image) throws NotFoundException;

  Result[] decodeMultiple(BinaryBitmap image, Hashtable hints) throws NotFoundException;

}
